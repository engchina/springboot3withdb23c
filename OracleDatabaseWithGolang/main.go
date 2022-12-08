package main

import (
	"database/sql"
	"encoding/json"
	"fmt"
	_ "github.com/sijms/go-ora/v2"
)

var dbParams = map[string]string{
	"username": "pdbadmin",
	"password": "oracle",
	"server":   "192.168.31.23",
	"port":     "1521",
	"service":  "pdb1",
}

func main() {
	connectionString := "oracle://" + dbParams["username"] + ":" + dbParams["password"] + "@" + dbParams["server"] + ":" + dbParams["port"] + "/" + dbParams["service"]
	db, err := sql.Open("oracle", connectionString)
	if err != nil {
		panic(fmt.Errorf("error in sql.Open: %w", err))
	}

	defer func() {
		err = db.Close()
		if err != nil {
			fmt.Println("Can't close connection: ", err)
		}
	}()

	err = db.Ping()
	if err != nil {
		panic(fmt.Errorf("error in db.Ping: %w", err))
	}

	sqlStatement := "SELECT REGION_ID, REGION_NAME FROM REGION WHERE REGION_ID = :1"
	stmt, err := db.Prepare(sqlStatement)
	if err != nil {
		panic(fmt.Errorf("error in db.Prepare: %w", err))
	}

	defer func() {
		err = stmt.Close()
		if err != nil {
			fmt.Println("Can't close stmt: ", err)
		}
	}()

	rows, err := stmt.Query("ap-tokyo-1") // 输入sql中对应参数的值
	if err != nil {
		panic(fmt.Errorf("error in stmt.Query: %w", err))
	}

	defer func() {
		err = rows.Close()
		if err != nil {
			fmt.Println("Can't close rows: ", err)
		}
	}()

	columns, _ := rows.Columns()
	for i := 0; i < len(columns); i++ {
		fmt.Print(" ", columns[i])
	}
	fmt.Print("\n")

	// 构造切片存储json
	var slice []map[string]interface{}
	var m map[string]interface{}
	m = make(map[string]interface{})

	var RegionId, RegionName string
	for rows.Next() {
		err := rows.Scan(&RegionId, &RegionName)
		if err != nil {
			panic(fmt.Errorf("error in rows.Scan: %w", err))
		}
		// 写入查询数据集的所有列名称
		fmt.Printf("REGION_ID is %s, REGION_NAME is %s\n", RegionId, RegionName)
		m["REGION_ID"] = RegionId
		m["REGION_NAME"] = RegionName
		slice = append(slice, m) // 分片中追加信息
	}
	if err = rows.Err(); err != nil {
		panic(fmt.Errorf("error in rows.Err: %w", err))
	}

	data, err := json.Marshal(slice)
	if err != nil {
		fmt.Printf("序列化错误 err = %v\n", err)
	} else {
		// 输出序列化后的结果 json字符串
		fmt.Printf("序列化后 = %v\n", string(data))
	}
}
