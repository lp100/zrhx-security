package org.com.zrhx.utill.tree;
/**
 * 带有复选框的树形封装对象
 * @author gs
 *
 */
public class CheckTreeNode {
	private String id;
	private String pId;
	private String name;
	private boolean open;
	private boolean checked;
	private String iconSkin;// 节点图标
	private boolean isParent;// 是否是父类节点
	private Object data;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public CheckTreeNode(String id, String pId, String name, boolean checked) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.open = true;
		this.checked = checked;
	}

	public CheckTreeNode(String id, String pId, String name, boolean checked,
			String iconSkin) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.open = true;
		this.checked = checked;
		this.iconSkin = iconSkin;
		isParent = false;
	}

	public CheckTreeNode(String id, String pId, String name, boolean checked,
			String iconSkin, boolean isParent) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.open = true;
		this.checked = checked;
		this.iconSkin = iconSkin;
		this.isParent = isParent;
	}

	public String getIconSkin() {
		return iconSkin;
	}

	public void setIconSkin(String iconSkin) {
		this.iconSkin = iconSkin;
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

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean getIsParent() {
		return isParent;
	}

	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}

}
