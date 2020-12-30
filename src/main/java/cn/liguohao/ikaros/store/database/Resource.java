package cn.liguohao.ikaros.store.database;

import javax.persistence.*;

/**资源
 * @author liguohao_cn
 * @date 2020/12/29
 */
@Entity
@Table(name = "resource")
public class Resource {

    /**
     * 资源ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "resource_id")
    private Long resourceId;

}
