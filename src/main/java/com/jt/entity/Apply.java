package com.jt.entity;

import java.util.Date;

public class Apply {
    private Integer id;

    private String name;

    private String phone;

    private Integer age;

    private Integer insureYear;

    private Integer educationLevel;
    
    //新加入的报考学历
    private Integer enterEducationLevel;

    private Integer customerId;

    private Integer isMember;

    private Integer memberId;

    private String memberName;
    
    private Integer rootMemberId;
    
    private String rootMemberName;

    private Integer state;

    private Date updateTime;

    private Date createTime;
    
    //新加入的关键字
    private String keyword;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getInsureYear() {
        return insureYear;
    }

    public void setInsureYear(Integer insureYear) {
        this.insureYear = insureYear;
    }

    public Integer getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(Integer educationLevel) {
        this.educationLevel = educationLevel;
    }

    
    public Integer getEnterEducationLevel() {
		return enterEducationLevel;
	}

	public void setEnterEducationLevel(Integer enterEducationLevel) {
		this.enterEducationLevel = enterEducationLevel;
	}

	public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getIsMember() {
        return isMember;
    }

    public void setIsMember(Integer isMember) {
        this.isMember = isMember;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
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

	public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "Apply [id=" + id + ", name=" + name + ", phone=" + phone + ", age=" + age + ", insureYear=" + insureYear
				+ ", educationLevel=" + educationLevel + ", enterEducationLevel=" + enterEducationLevel
				+ ", customerId=" + customerId + ", isMember=" + isMember + ", memberId=" + memberId + ", memberName="
				+ memberName + ", rootMemberId=" + rootMemberId + ", rootMemberName=" + rootMemberName + ", state="
				+ state + ", updateTime=" + updateTime + ", createTime=" + createTime + ", keyword=" + keyword + "]";
	}
    
    
}