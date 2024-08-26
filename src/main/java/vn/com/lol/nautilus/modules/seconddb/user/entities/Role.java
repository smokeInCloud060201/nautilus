package vn.com.lol.nautilus.modules.seconddb.user.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;
import vn.com.lol.common.entities.BaseEntity;
import vn.com.lol.nautilus.commons.constant.HibernateConstant;

import java.io.Serializable;
import java.util.List;

import static vn.com.lol.common.constants.GlobalHibernateConstant.IS_NOT_DELETED;


@Getter
@Setter
@NoArgsConstructor
@Entity(name = HibernateConstant.Entity.ROLE)
@Table(name = HibernateConstant.Table.ROLE)
@SQLRestriction(IS_NOT_DELETED)
public class Role extends BaseEntity implements Serializable {
    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    @JsonManagedReference
    private List<Permission> rolePermission;

    @ManyToMany(mappedBy = "userRoles")
    @JsonBackReference
    private List<User> roleUser;

}
