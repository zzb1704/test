package admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.base.Strings;

import admin.common.AppPermission;
import admin.common.Keys;
import ec.common.JSonUtils;
import ec.common.Permission;
import ec.common.RequestUtils;
import ec.model.sys.SysUser;

//视图控制器
@Controller
@RequestMapping("view")
public class ViewController extends BaseController {

	
	 //权限不足
	 @RequestMapping("/nopermission") 
	 public String noPermission() { 
	 return "nopermission"; 
	 }
	 

	@ModelAttribute("sysuser")
	public SysUser getSysUser(HttpServletRequest request) {
		String userString = RequestUtils.getCookieValue(request, Keys.LOGIN_USER);
		SysUser user = null;
		if (!Strings.isNullOrEmpty(userString)) {
			user = JSonUtils.readValue(userString, SysUser.class);
		}
		return user;
	}

	// 会员查询
	//@Permission(AppPermission.Customer_Search)
	//@RequestMapping("user/list")
	/*public String viewUser() {
		return "user/user_list";
	}*/

	// 会员新增
	@Permission(AppPermission.Customer_Add)
	@RequestMapping("layer/user/add")
	public String addUser() {
		return "user/user_add";
	}
	
	// 会员修改
	@Permission(AppPermission.Customer_Edit)
	@RequestMapping("layer/user/update")
	public String UpdateUser() {
		return "user/user_update";
	}
	// 会员角色查询
	@Permission(AppPermission.Customer_Role_Search)
	@RequestMapping("user/role/list")
	public String viewRoleUser() {
		return "user/role/role_list";
	}
	// 会员角色编辑
	@Permission(AppPermission.Customer_Role_Edit)
	@RequestMapping("layer/user/role/update")
	public String updateRoleUser() {
		return "user/role/role_update";
	}

	// 会员角色新增
	@Permission(AppPermission.Customer_Role_Add)
	@RequestMapping("layer/user/role/add")
	public String addRoleUser() {
		return "user/role/role_add";
	}
	
	// 会员关联会员角色
	//@Permission(AppPermission.Customer_Ref_Role_Search)
	//@RequestMapping("layer/user/refrole")
	/*public String viewUserRefUserRole() {
		return "user/user_refrole";
	}*/
	
	// 特权新增
	@Permission(AppPermission.Privilege_Add)
	@RequestMapping("layer/privilege/add")
	public String AddPrivilege() {
		return "privilege/privilege_add";
	}

	// 特权修改
	@Permission(AppPermission.Privilege_Edit)
	@RequestMapping("layer/privilege/update")
	public String UpdatePrivilege() {
		return "privilege/privilege_update";
	}
	
	// 特权查询
	@Permission(AppPermission.Privilege_Search)
	@RequestMapping("privilege/list")
	public String viewPrivilege() {
		return "privilege/privilege_list";
	}
	
	// 系统角色查询
	@Permission(AppPermission.Role_Search)
	@RequestMapping("role/list")
	public String viewSysRole() {
		return "role/role_list";
	}
	// 系统角色编辑
	@Permission(AppPermission.Role_Edit)
	@RequestMapping("layer/sys/role/update")
	public String updateSysRole() {
		return "sys/role/role_update";
	}

	// 系统角色新增
	@Permission(AppPermission.Role_Add)
	@RequestMapping("layer/sys/role/add")
	public String addSysdRole() {
		return "sys/role/role_add";
	}
	
	// 系统用户关联系统角色
	@Permission(AppPermission.SysUser_Ref_Role_Search)
	@RequestMapping("layer/sys/sysuser/refrole")
	public String viewSysUserRefSysRole() {
		return "sys/sysuser/sysuser_refrole";
	}
	// 系统用户查询
	@Permission(AppPermission.SysUser_Search)
	@RequestMapping("sys/sysuser/list")
	public String viewSysUserList() {
		return "sys/sysuser/sysuser_list";
	}

	// 系统用户新增
	@Permission(AppPermission.SysUser_Add)
	@RequestMapping("layer/sys/sysuser/add")
	public String viewSysUserAdd() {
		return "sys/sysuser/sysuser_add";
	}

	// 系统用户修改
	@Permission(AppPermission.SysUser_Edit)
	@RequestMapping("layer/sys/sysuser/update")
	public String UpdateSysUser() {
		return "sys/sysuser/sysuser_update";
	}

	// 系统角色关联系统权限
	@Permission(AppPermission.Role_Ref_Privilege_Search)
	@RequestMapping("layer/sys/role/privilege")
	public String viewSysRoleRefPrivilege() {
		return "sys/role/role_privilege";
	}
	
	// 系统用户关联菜单
	@Permission(AppPermission.SysUser_Ref_Menu_Search)
	@RequestMapping("layer/sys/sysuser/refmenu")
	public String viewSysUserRefMenu() {
		return "sys/sysuser/sysuser_refmenu";
	}
	
	// 短信息查询
	@Permission(AppPermission.SMS_Search)
	@RequestMapping("sms/list")
	public String viewSMS() {
		return "sms/sms_list";
	}
	
	// 动态查询
	@Permission(AppPermission.Dynamic_Search)
	@RequestMapping("dynamic/list")
	public String ViewDynaminc() {
		return "dynamic/dynamic_list";
	}

	// 动态新增
	@Permission(AppPermission.Dynamic_Add)
	@RequestMapping("layer/dynamic/add")
	public String AddDynaminc() {
		return "dynamic/dynamic_add";
	}

	// 动态编辑
	@Permission(AppPermission.Dynamic_Edit)
	@RequestMapping("layer/dynamic/update")
	public String UpdateDynaminc() {
		return "dynamic/dynamic_update";
	}

	// 动态关联评论新增
	@Permission(AppPermission.Dynamic_Ref_Comment_Search)
	@RequestMapping("layer/dynamic/addcomment")
	public String ViewRefComment() {
		return "dynamic/dynamic_addcomment";
	}

	// 动态关联评论查询
	@Permission(AppPermission.Dynamic_Ref_Comment_Search)
	@RequestMapping("layer/dynamic/refcomment")
	public String AddRefComment() {
		return "dynamic/dynamic_refcomment";
	}

	// 雇佣关系查询
	@Permission(AppPermission.Employ_Search)
	@RequestMapping("employ/list")
	public String viewEmploy() {
		return "employ/employ_list";
	}

	// 雇佣关系编辑
	@Permission(AppPermission.Employ_Edit)
	@RequestMapping("layer/employ/update")
	public String updateEmploy() {
		return "employ/employ_update";
	}

	// 雇佣关系新增
	@Permission(AppPermission.Employ_Add)
	@RequestMapping("layer/employ/add")
	public String AddEmploy() {
		return "employ/employ_add";
	}
	
	// 查询所有地主
	@Permission(AppPermission.Employ_Add)
	@RequestMapping("layer/employ/searchuser")
	public String addEmploySearchUser() {
		return "employ/employ_searchuser";
	}
	
	// 查询所有短工
	@Permission(AppPermission.Employ_Add)
	@RequestMapping("layer/employ/searchemployee")
	public String addEmploySearchEmployee() {
		return "employ/employ_searchemployee";
	}
	

	// 农场查询
	@Permission(AppPermission.Farm_Search)
	@RequestMapping("farm/list")
	public String viewFarm() {
		return "farm/farm_list";
	}

	// 农场编辑
	@Permission(AppPermission.Farm_Edit)
	@RequestMapping("layer/farm/update")
	public String updateFarm() {
		return "farm/farm_update";
	}

	// 农场新增
	@Permission(AppPermission.Farm_Add)
	@RequestMapping("layer/farm/add")
	public String addFarm() {
		return "farm/farm_add";
	}

	// 菜园查询
	@Permission(AppPermission.Farm_Land_Search)
	@RequestMapping("farmland/list")
	public String viewFarmLand() {
		return "farmland/farmland_list";
	}
	
	// 菜园编辑
	@Permission(AppPermission.Farm_Land_Edit)
	@RequestMapping("farmland/update")
	public String updateFarmLand() {
		return "farmland/farmland_update";
	}
	// 菜园新增
	@Permission(AppPermission.Farm_Land_Add)
	@RequestMapping("farmland/add")
	public String addFarmLand() {
		return "farmland/farmland_add";
	}
	
	// 菜园关联地主
	@Permission(AppPermission.Farm_Land_Ref_Owner_Search)
	@RequestMapping("farmland/refowner")
	public String viewFarmLandRefOwner() {
		return "farmland/farmland_refowner";
	}
	
	// 菜园关联地主页查询所有地主
	@Permission(AppPermission.Farm_Land_Ref_Owner_Search)
	@RequestMapping("farmland/searchuser")
	public String viewFarmLandSearchUser() {
		return "farmland/farmland_searchuser";
	}
	
	// 菜园一级任务查询
	@Permission(AppPermission.Farm_Land_Task_Master_Search)
	@RequestMapping("farmland/task/master/list")
	public String ViewMaster() {
		return "farmland/task/master/master_list";
	}

	// 菜园一级任务编辑
	@Permission(AppPermission.Farm_Land_Task_Master_Edit)
	@RequestMapping("layer/farmland/task/master/update")
	public String updateMaster() {
		return "farmland/task/master/master_update";
	}
	// 菜园一级任务新增
	@Permission(AppPermission.Farm_Land_Task_Master_Edit)
	@RequestMapping("layer/farmland/task/master/add")
	public String addMaster() {
		return "farmland/task/master/master_add";
	}
	
	// 菜园二级任务查询
	@Permission(AppPermission.Farm_Land_Task_Item_Search)
	@RequestMapping("farmland/task/item/list")
	public String ViewItem() {
		return "farmland/task/item/item_list";
	}

	// 菜园二级任务编辑
	@Permission(AppPermission.Farm_Land_Task_Master_Edit)
	@RequestMapping("layer/farmland/task/item/update")
	public String updateItem() {
		return "farmland/task/master/item_update";
	}
	// 菜园二级任务新增
	@Permission(AppPermission.Farm_Land_Task_Master_Edit)
	@RequestMapping("layer/farmland/task/item/add")
	public String addItem() {
		return "farmland/task/item/item_add";
	}
	
	// 多图查询
	@Permission(AppPermission.Media_Image_Search)
	@RequestMapping("layer/media/list")
	public String viewMediaImage() {
		return "media/media_list";
	}

	// 多图新增
	@Permission(AppPermission.Media_Image_Add)
	@RequestMapping("layer/media/add")
	public String addMediaImage() {
		return "media/media_add";
	}

	// 多图修改
	@Permission(AppPermission.Media_Image_Edit)
	@RequestMapping("layer/media/update")
	public String updateActiveImage() {
		return "media/media_update";
	}

	// 图文查询
	@Permission(AppPermission.Media_Detail_Image_Search)
	@RequestMapping("layer/media/detail/list")
	public String ViewMediaDetailImage() {
		return "media/detail/detail_list";
	}

	// 图文新增
	@Permission(AppPermission.Media_Detail_Image_Edit)
	@RequestMapping("layer/media/detail/add")
	public String addMediaDetailImage() {
		return "media/detail/detail_add";
	}

	// 图文修改
	@Permission(AppPermission.Media_Detail_Image_Add)
	@RequestMapping("layer/media/detail/update")
	public String updateMediaDetailImage() {
		return "media/detail/detail_update";
	}
	
	// 客服发出任务通知查询
	@Permission(AppPermission.Farm_Land_Ref_Task_Notice_Search)
	@RequestMapping("layer/servant/notice/list")
	public String viewTaskNotice() {
		return "servant/notice/notice_list";
	}

	// 客服发出任务通知新增
	@Permission(AppPermission.Farm_Land_Ref_Task_Notice_Add)
	@RequestMapping("layer/servant/notice/add")
	public String addTaskNotice() {
		return "servant/notice/notice_add";
	}

	// 客服发出任务通知编辑
	@Permission(AppPermission.Farm_Land_Ref_Task_Notice_Edit)
	@RequestMapping("layer/servant/notice/update")
	public String updateTaskNotice() {
		return "servant/notice/notice_update";
	}
	// 客服查询
	@Permission(AppPermission.Farm_Servant_Search)
	@RequestMapping("servant/list")
	public String viewServant() {
		return "servant/servant_list";
	}

	// 客服新增
	@Permission(AppPermission.Farm_Servant_Add)
	@RequestMapping("layer/servant/add")
	public String AddServant() {
		return "servant/servant_add";
	}

	// 客服修改
	@Permission(AppPermission.Farm_Servant_Edit)
	@RequestMapping("layer/servant/update")
	public String UpdateServant() {
		return "servant/servant_update";
	}

	// 客服关联土地查询
	@Permission(AppPermission.Farm_Servant_Ref_Land_Search)
	@RequestMapping("layer/servant/refland")
	public String ViewServantRefLand() {
		return "servant/servant_refland";
	}

	// 客服关联土地新增
	@Permission(AppPermission.Farm_Servant_Ref_Land_Add)
	@RequestMapping("layer/sevant/addrefland")
	public String addServantRefLand() {
		return "servant/servant_addrefland";
	}

	// 客服关联土地编辑
	@Permission(AppPermission.Farm_Servant_Ref_Land_Edit)
	@RequestMapping("layer/servant/updaterefland")
	public String updateServantRefLand() {
		return "servant/servant_updaterefland";
	}
	
	// 客服关联土地编辑
	@Permission(AppPermission.Farm_Servant_Ref_Land_Edit)
	@RequestMapping("layer/servant/searchfland")
	public String searchFarmLand() {
		return "servant/servant_searchfland";
	}
	

	// 一级菜单查询
	@Permission(AppPermission.Menu1_Search)
	@RequestMapping("sys/menu1/list")
	public String ViewMenu1() {
		return "sys/menu1/menu1_list";
	}

	// 一级菜单新增
	@Permission(AppPermission.Menu1_Add)
	@RequestMapping("layer/sys/menu1/add")
	public String AddMenu1() {
		return "sys/menu1/menu1_add";
	}

	// 一级菜单修改
	@Permission(AppPermission.Menu1_Edit)
	@RequestMapping("layer/sys/menu1/update")
	public String UpdateMenu1() {
		return "sys/menu1/menu1_update";
	}

	// 二级菜单查询
	@Permission(AppPermission.Menu2_Search)
	@RequestMapping("sys/menu2/list")
	public String ViewMenu2() {
		return "sys/menu2/menu2_list";
	}

	// 二级菜单新增
	@Permission(AppPermission.Menu2_Add)
	@RequestMapping("layer/sys/menu2/add")
	public String AddMenu2() {
		return "sys/menu2/menu2_add";
	}

	// 二级菜单修改
	@Permission(AppPermission.Menu2_Edit)
	@RequestMapping("layer/sys/menu2/update")
	public String UpdateMenu2() {
		return "sys/menu2/menu2_update";
	}

	// 三级菜单查询
	@Permission(AppPermission.Menu3_Search)
	@RequestMapping("sys/menu3/list")
	public String ViewMenu3() {
		return "sys/menu3/menu3_list";
	}

	// 三级菜单新增
	@Permission(AppPermission.Menu3_Add)
	@RequestMapping("layer/sys/menu3/add")
	public String AddMenu3() {
		return "sys/menu3/menu3_add";
	}

	// 三级菜单修改
	@Permission(AppPermission.Menu3_Edit)
	@RequestMapping("layer/sys/menu3/update")
	public String UpdateMenu3() {
		return "sys/menu3/menu3_update";
	}

}
