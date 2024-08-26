package vn.com.lol.nautilus.modules.seconddb.user.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Entity(name = HibernateConstant.Entity.PERMISSION)
@Table(name = HibernateConstant.Table.PERMISSION)
@SQLRestriction(IS_NOT_DELETED)
public class Permission extends BaseEntity implements Serializable {
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "rolePermission")
    @JsonBackReference
    private List<Role> permissionRoles;
}
