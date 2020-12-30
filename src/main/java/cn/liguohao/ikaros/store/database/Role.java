package cn.liguohao.ikaros.store.database;

import javax.persistence.*;

/**角色
 * @author liguohao_cn
 * @date 2020/12/29
 */
@Entity
@Table(name = "role")
public class Role {


    /**
     * 角色ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Long roleId;


    public static Role build(){
        return new Role();
    }

    public Long getRoleId() {
        return roleId;
    }

    public Role setRoleId(Long roleId) {
        this.roleId = roleId;
        return this;
    }
}
