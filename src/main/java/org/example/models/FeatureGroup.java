package org.example.models;

import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;

@Entity
@Table(name = FeatureGroup.TABLE_NAME, indexes = @Index(name = "sp_feature_group_strcode_idx", columnList = "strcode", unique = true))
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FeatureGroup implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String TABLE_NAME = "sp_feature_group";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 50, nullable = false)
	private String strcode;

	@Column(nullable = false)
	private String name;

	@ManyToOne(targetEntity = Category.class, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable = false, updatable = true)
	private Category category;

	@Column(name = "order_num")
	private Integer orderNum;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStrcode() {
		return strcode;
	}

	public void setStrcode(String strcode) {
		this.strcode = strcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((strcode == null) ? 0 : strcode.hashCode());
		result = prime * result + ((orderNum == null) ? 0 : orderNum.hashCode());
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
		final FeatureGroup other = (FeatureGroup) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (strcode == null) {
			if (other.strcode != null)
				return false;
		} else if (!strcode.equals(other.strcode))
			return false;
		if (orderNum == null) {
			if (other.orderNum != null)
				return false;
		} else if (!orderNum.equals(other.orderNum))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FeatureGroup [id=" + id + ", strcode=" + strcode + ", name=" + name + ", orderNum=" + orderNum + "]";
	}

}
