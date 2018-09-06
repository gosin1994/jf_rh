package com.jt.entity;

import java.util.Date;

public class Member implements Comparable<Member>{
    private Integer id;

    private String name;

    private String phone;

    private Integer pid;

    private Integer level;
    
    private Integer orderCount;

    private String bankName;

    private String bankAccountName;

    private String bankAccountNo;
    
    private String alipayNo;

    private String qrcodeUrl;

    private Integer customerId;
    
    private Integer rootMemberId;
    
    private String rootMemberName;
    
    private Date updateTime;

    private Date createTime;
    
    private Integer totalAmount;
    
    private String pidName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    
    public Integer getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}

	public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName == null ? null : bankAccountName.trim();
    }

    public String getBankAccountNo() {
        return bankAccountNo;
    }

    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo == null ? null : bankAccountNo.trim();
    }

    public String getAlipayNo() {
		return alipayNo;
	}

	public void setAlipayNo(String alipayNo) {
		this.alipayNo = alipayNo;
	}

	public String getQrcodeUrl() {
        return qrcodeUrl;
    }

    public void setQrcodeUrl(String qrcodeUrl) {
        this.qrcodeUrl = qrcodeUrl == null ? null : qrcodeUrl.trim();
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    
    public Integer getRootMemberId() {
		return rootMemberId;
	}

	public void setRootMemberId(Integer rootMemberId) {
		this.rootMemberId = rootMemberId;
	}

	public String getRootMemberName() {
		return rootMemberName;
	}

	public void setRootMemberName(String rootMemberName) {
		this.rootMemberName = rootMemberName;
	}

	public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public Integer getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public int compareTo(Member o) {
		return getCreateTime().compareTo(o.getCreateTime()) * -1;
	}

	public String getPidName() {
		return pidName;
	}

	public void setPidName(String pidName) {
		this.pidName = pidName;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", phone=" + phone + ", pid=" + pid + ", level=" + level
				+ ", orderCount=" + orderCount + ", bankName=" + bankName + ", bankAccountName=" + bankAccountName
				+ ", bankAccountNo=" + bankAccountNo + ", alipayNo=" + alipayNo + ", qrcodeUrl=" + qrcodeUrl
				+ ", customerId=" + customerId + ", rootMemberId=" + rootMemberId + ", rootMemberName=" + rootMemberName
				+ ", updateTime=" + updateTime + ", createTime=" + createTime + ", totalAmount=" + totalAmount
				+ ", pidName=" + pidName + "]";
	}
	
	
}