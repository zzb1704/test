package admin.common;

public class AppPermission {
	
	public final static int Adminsuper = 9999;
	// 会员-查询
	public final static int Customer_Search = 1;
	// 会员-编辑
	public final static int Customer_Edit = 2;
	// 会员-新增
	public final static int Customer_Add = 3;
	// 会员-删除
	public final static int Customer_Del = 4;

	// 会员角色查询
	public final static int Customer_Role_Search = 5;
	// 会员角色编辑
	public final static int Customer_Role_Edit = 6;
	// 会员角色新增
	public final static int Customer_Role_Add = 7;

	// 会员关联角色查询
	public final static int Customer_Ref_Role_Search = 8;
	// 会员关联角色编辑
	public final static int Customer_Ref_Role_Edit = 9;
	// 会员关联角色新增
	public final static int Customer_Ref_Role_Add = 10;
	// 会员关联角色删除
	public final static int Customer_Ref_Role_Del = 11;

	// 菜单查询
	public final static int Menu_Search = 12;
	// 菜单编辑
	public final static int Menu_Edit = 13;
	// 菜单新增
	public final static int Menu_Add = 14;

	// 特权查询
	public final static int Privilege_Search = 15;
	// 特权编辑
	public final static int Privilege_Edit = 16;
	// 特权新增
	public final static int Privilege_Add = 17;

	// 系统角色查询
	public final static int Role_Search = 18;
	// 系统角色编辑
	public final static int Role_Edit = 19;
	// 系统角色新增
	public final static int Role_Add = 20;

	// 系统用户-角色查询
	public final static int SysUser_Ref_Role_Search = 21;
	// 系统用户-角色编辑
	public final static int SysUser_Ref_Role_Edit = 22;
	// 系统用户-角色新增
	public final static int SysUser_Ref_Role_Add = 23;
	// 系统用户-角色删除
	public final static int SysUser_Ref_Role_Del = 24;

	// 系统用户查询
	public final static int SysUser_Search = 25;
	// 系统用户编辑
	public final static int SysUser_Edit = 26;
	// 系统用户新增
	public final static int SysUser_Add = 27;

	// 系统角色-权限查询
	public final static int Role_Ref_Privilege_Search = 28;
	// 系统角色-权限编辑
	public final static int Role_Ref_Privilege_Edit = 29;
	// 系统角色-权限新增
	public final static int Role_Ref_Privilege_Add = 30;
	// 系统角色-权限删除
	public final static int Role_Ref_Privilege_Del = 31;

	// 系统用户-菜单查询
	public final static int SysUser_Ref_Menu_Search = 32;
	// 系统用户-菜单编辑
	public final static int SysUser_Ref_Menu_Edit = 33;
	// 系统用户-菜单新增
	public final static int SysUser_Ref_Menu_Add = 34;
	// 系统用户-菜单删除
	public final static int SysUser_Ref_Menu_Del = 35;

	
	// 短信查询
	public final static int SMS_Search = 36;

	// 动态查询
	public final static int Dynamic_Search = 37;
	// 动态编辑
	public final static int Dynamic_Edit = 38;
	// 动态新增
	public final static int Dynamic_Add = 39;

	// 动态关联评论查询
	public final static int Dynamic_Ref_Comment_Search = 40;
	// 动态关联评论编辑
	public final static int Dynamic_Ref_Comment_Edit = 41;
	// 动态关联评论新增
	public final static int Dynamic_Ref_Comment_Add = 42;

	// 雇佣关系查询
	public final static int Employ_Search = 43;
	// 雇佣关系编辑
	public final static int Employ_Edit = 44;
	// 雇佣关系新增
	public final static int Employ_Add = 45;

	// 农场查询
	public final static int Farm_Search = 46;
	// 农场编辑
	public final static int Farm_Edit = 47;
	// 农场新增
	public final static int Farm_Add = 48;

	// 农场菜园查询
	public final static int Farm_Land_Search = 49;
	// 农场菜园编辑
	public final static int Farm_Land_Edit = 50;
	// 农场菜园新增
	public final static int Farm_Land_Add = 51;

	// 农场菜园关联地主查询
	public final static int Farm_Land_Ref_Owner_Search = 52;
	// 农场菜园关联地主编辑
	public final static int Farm_Land_Ref_Owner_Edit = 53;
	// 农场菜园关联地主新增
	public final static int Farm_Land_Ref_Owner_Add = 54;

	// 农场菜园一级任务查询
	public final static int Farm_Land_Task_Master_Search = 55;
	// 农场菜园一级任务编辑
	public final static int Farm_Land_Task_Master_Edit = 56;
	// 农场菜园一级任务新增
	public final static int Farm_Land_Task_Master_Add = 57;

	// 农场菜园二级任务查询
	public final static int Farm_Land_Task_Item_Search = 58;
	// 农场菜园二级任务编辑
	public final static int Farm_Land_Task_Item_Edit = 59;
	// 农场菜园二级任务新增
	public final static int Farm_Land_Task_Item_Add = 60;
	
	// 图文查询
	public final static int Media_Detail_Image_Search = 61;
	// 图文编辑
	public final static int Media_Detail_Image_Edit = 62;
	// 图文新增
	public final static int Media_Detail_Image_Add = 63;
	
	// 多图查询
	public final static int Media_Image_Search = 64;
	// 多图编辑
	public final static int Media_Image_Edit = 65;
	// 多图新增
	public final static int Media_Image_Add = 66;
	
	// 客服任务通知查询
	public final static int Farm_Land_Ref_Task_Notice_Search = 67;
	// 客服任务通知编辑
	public final static int Farm_Land_Ref_Task_Notice_Edit = 68;
	// 客服任务通知新增
	public final static int Farm_Land_Ref_Task_Notice_Add = 69;
	
	// 客服查询
	public final static int Farm_Servant_Search = 70;
	// 客服编辑
	public final static int Farm_Servant_Edit = 71;
	// 客服新增
	public final static int Farm_Servant_Add = 72;
	
	// 客服关联土地
	public final static int Farm_Servant_Ref_Land_Search = 73;
	// 客服关联土地
	public final static int Farm_Servant_Ref_Land_Edit = 74;
	// 客服关联土地
	public final static int Farm_Servant_Ref_Land_Add = 75;
	
	// 一级菜单查询
	public final static int Menu1_Search = 12;
	// 一级菜单编辑
	public final static int Menu1_Edit = 13;
	// 一级菜单新增
	public final static int Menu1_Add = 14;
	
	// 二级菜单查询
	public final static int Menu2_Search = 12;
	// 二级菜单编辑
	public final static int Menu2_Edit = 13;
	// 二级菜单新增
	public final static int Menu2_Add = 14;
	
	// 三级菜单查询
	public final static int Menu3_Search = 12;
	// 三级菜单编辑
	public final static int Menu3_Edit = 13;
	// 三级菜单新增
	public final static int Menu3_Add = 14;
	
}
