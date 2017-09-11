package com.onedirect.rule.executor.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class RuleBean implements Serializable {
    
	private static final long serialVersionUID = 1L;
   
    private Integer id;
    
    private Integer companyId;
    
    private String ruleName;
    
    private Integer rank;
    
    private long modifiedBy;
    
    private Date modifiedAt;
    
    private long createdBy;
    
    private Date createdAt;
    
    private RuleCondition conditions;
   
    private List<RuleActionBean> actions;

    public RuleBean() {
    }

    public RuleBean(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

	public RuleCondition getConditions() {
		return conditions;
	}

	public void setConditions(RuleCondition conditions) {
		this.conditions = conditions;
	}

	public List<RuleActionBean> getActions() {
		return actions;
	}

	public void setActions(List<RuleActionBean> actions) {
		this.actions = actions;
	}

	public long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RuleBean other = (RuleBean) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
