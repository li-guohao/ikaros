package cn.liguohao.ikaros.dao;

import cn.liguohao.ikaros.store.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @Description TODO
 * @Author liguohao
 * @Date 2020/11/25 20:24
 */
public interface FileDao extends JpaRepository<File,Long>, PagingAndSortingRepository<File,Long>, JpaSpecificationExecutor<File> {
}
