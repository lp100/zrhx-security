package org.com.zrhx.utill.tree;

/**
 * 
 * @ClassName: TreeNode;
 * 
 * @Description: 树类;
 * 
 * @date : 2010-8-4 上午10:24:38;
 * 
 * @author:Administrator
 */
public class TreeNode {
	private String id;
	private String pId;
	private String name;
	private boolean open;//展开或折叠
	private Object data;
	private boolean nocheck;//设置节点是否隐藏 checkbox / radio [
	private String iconSkin ;//节点图标
	private boolean drag;//拖拽
	private boolean doCheck;
	private boolean isParent;//是否是父类节点
	public TreeNode() {
		super();
	}

	public TreeNode(String id, String pId, String name, Object data) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.open = true;
		this.data = data;
		this.nocheck = true;
	}
	/**
	 * 自定义图标（不带复选框）
	 * @param id
	 * @param pId
	 * @param name
	 * @param data
	 * @param iconSkin
	 */
	public TreeNode(String id, String pId, String name, Object data,
			String iconSkin){
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.open = false;
		this.data = data;
		this.nocheck = true;
		this.iconSkin = iconSkin;
	}
	
	
	public TreeNode(String id, String pId, String name, Object data,
			String iconSkin,boolean drag,boolean open) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.data = data;
		this.iconSkin = iconSkin;
		this.drag = drag;
		this.open = open;
	}
	public TreeNode(String id, String pId, String name, Object data,boolean drag) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.data = data;
		this.drag = drag;
	}
	public TreeNode(String id, String pId, String name, Object data,boolean drag,boolean open) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.data = data;
		this.drag = drag;
		this.open = open;
	}
	public TreeNode(String id, String pId, String name, Object data,boolean drag,boolean open,boolean doCheck) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.data = data;
		this.drag = drag;
		this.open = open;
		this.doCheck = doCheck;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public boolean isNocheck() {
		return nocheck;
	}

	public void setNocheck(boolean nocheck) {
		this.nocheck = nocheck;
	}

	public String getIconSkin() {
		return iconSkin;
	}

	public void setIconSkin(String iconSkin) {
		this.iconSkin = iconSkin;
	}

	public boolean isDrag() {
		return drag;
	}

	public void setDrag(boolean drag) {
		this.drag = drag;
	}

	public boolean isDoCheck() {
		return doCheck;
	}

	public void setDoCheck(boolean doCheck) {
		this.doCheck = doCheck;
	}

	public boolean getIsParent() {
		return isParent;
	}

	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}
 
	

	
	

}
