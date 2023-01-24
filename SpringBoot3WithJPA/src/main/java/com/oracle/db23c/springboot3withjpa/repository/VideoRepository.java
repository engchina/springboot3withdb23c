package com.oracle.db23c.springboot3withjpa.repository;

import com.oracle.db23c.springboot3withjpa.entity.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// 它使用两个通用参数VideoEntity 和 Long（域类型和主键类型）扩展了 JpaRepository
// JpaRepository是一个Spring Data JPA接口，包含一组已经支持的更改替换更新删除（CRUD）操作。
public interface VideoRepository extends JpaRepository
        <VideoEntity, Long> {

    // 创建自定义查找器。我们永远不必实现此方法。Spring Data将按照本节所述为我们完成此操作。
    // 返回类型为 List<VideoEntity>，指示它必须返回存储库域类型的列表。
    // 所有以 findBy 开头的存储库方法都标记为查询。
    // select video.* from VideoEntity video where video.name = ?1
    List<VideoEntity> findByName(String name);

    // @Query是Spring Data JPA提供自定义JPQL语句的方式。
    @Query("select v from VideoEntity v where v.name = ?1")
    List<VideoEntity> findCustomerReport(String name);

    // Spring Data JPA 3.0 包含 JSqlParser，一个 SQL 解析库，可以按如下方式编写查询
    @Query(value="select * from VIDEO_ENTITY where NAME = ?1", nativeQuery=true)
    List<VideoEntity> findCustomWithPureSql(String name);

    List<VideoEntity> findByNameContainsIgnoreCase(String partialName);

    List<VideoEntity> findByDescriptionContainsIgnoreCase(String partialDescription);

    List<VideoEntity> findByNameContainsOrDescriptionContainsAllIgnoreCase(String partialName,
                                                                           String partialDescription);
}
