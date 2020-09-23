package com.krupp.cloud.common.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Set;

@Data
public class SysUser extends User {

	/** 用户ID */

	private Long id;

	/** 部门ID */

	private Long deptId;

	/** 用户所拥有权限标识 */

	private Set<String> permissions;

	/**
	 * 构造用户详情信息
     *
	 * <p>
	 * 以下为security userDetail 用户详情信息参数
	 * </p>
	 * @param username 用户名
	 * @param password 用户密码
	 * @param enabled 用户是否启用 默认为true（和accountNonLocked有点歧义，个人理解为是用户是否被删除）
	 * @param accountNonExpired 账户是否未过期 默认为true
	 * @param credentialsNonExpired 证书是否未过期 默认为ture
	 * @param accountNonLocked 账号是否未被冻结 默认为true
	 * @param authorities 账号密码验证通过后用户所拥有的权限，eg:"admin","user"等角色权限，不能为null
     *
     * <p>
     * 以下为系统自定义扩展参数
     * </p>
	 * @param id 用户ID
	 * @param deptId 用户部门ID
	 * @param permissions 用户所拥有的权限标识
	 */
	public SysUser(Long id, Long deptId, Set<String> permissions, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.id = id;
		this.deptId = deptId;
		this.permissions = permissions;
	}
}
