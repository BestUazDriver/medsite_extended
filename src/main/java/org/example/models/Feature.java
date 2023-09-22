package org.example.models;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Характеристика (которой может обладать сервис)
 */
@Entity
@Table(name = Feature.TABLE_NAME, indexes = @Index(name = "sp_feature_strcode_idx", columnList = "strcode", unique = true))
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Feature implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String TABLE_NAME = "sp_feature";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 50, nullable = false)
	private String strcode;

	@Column(length = 100, nullable = false)
	private String name;

//	@Column(nullable = false)
//	@Enumerated(EnumType.STRING)
//	private FeatureType type;

	@ManyToOne(targetEntity = Category.class, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable = false, updatable = false)
	private Category category;

	@ManyToOne(targetEntity = FeatureGroup.class, optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "feature_group_id", updatable = true)
	private FeatureGroup featureGroup;

	@Column(name = "in_search")
	private Boolean inSearch = Boolean.FALSE;

	@Column(name = "order_num")
	private Integer orderNum;

//	@OneToMany(targetEntity = FeatureEnum.class, mappedBy = "feature", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
//	@OrderBy("order_num")
//	private List<FeatureEnum> enumValues;

	@Column(columnDefinition = "longtext")
	private String prompt;

	@Column(name = "has_default")
	private Boolean hasDefault;

	@Column(name = "default_str_value")
	private String defaultString;

	@Column(name = "default_bool_value")
	private Boolean defaultBool;

	@Column(name = "default_num_value")
	private Float defaultNum;

	/** Количество голосов за важность данной характеристики */
	@Column(name = "importance_votes")
	@ColumnDefault("0")
	private Integer importanceVotes;

	@Transient
	private String featureNameDisplay;

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

	public String getNameDisplay() {
		if (StringUtils.isNotEmpty(featureNameDisplay))
			return featureNameDisplay;
		if (StringUtils.isBlank(prompt) || StringUtils.isBlank(name))
			return name;
		int end = name.lastIndexOf(" ");
		if (end > 0) {
			String stringEnd = " <dfn>" + name.substring(end + 1);
			featureNameDisplay = name.replaceFirst(" \\S+$", stringEnd);
		} else
			featureNameDisplay = name;
		return featureNameDisplay;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public FeatureType getType() {
//		return type;
//	}
//
//	public void setType(FeatureType type) {
//		this.type = type;
//	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public FeatureGroup getFeatureGroup() {
		return featureGroup;
	}

	public void setFeatureGroup(FeatureGroup featureGroup) {
		this.featureGroup = featureGroup;
	}

	public Boolean getInSearch() {
		return inSearch == null ? Boolean.FALSE : inSearch;
	}

	public void setInSearch(Boolean inSearch) {
		this.inSearch = inSearch == null ? Boolean.FALSE : inSearch;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

//	public List<FeatureEnum> getEnumValues() {
//		return enumValues;
//	}
//
//	public void setEnumValues(List<FeatureEnum> enumValues) {
//		this.enumValues = enumValues;
//	}
//
//	public FeatureEnum getEnumValueDefault() {
//		for (FeatureEnum enumVal : enumValues)
//			if (BooleanUtils.isTrue(enumVal.getDefaultValue()))
//				return enumVal;
//		return null;
//	}
//
//	public List<FeatureEnum> getMultValueDefaults() {
//		List<FeatureEnum> defaults = new ArrayList<>();
//		for (FeatureEnum enumVal : enumValues) {
//			if (BooleanUtils.isTrue(enumVal.getDefaultValue())) {
//				defaults.add(enumVal);
//			}
//		}
//		return defaults;
//	}

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public Boolean getHasDefault() {
		return hasDefault;
	}

	public void setHasDefault(Boolean hasDefault) {
		this.hasDefault = hasDefault;
	}

	public String getDefaultString() {
		return defaultString;
	}

	public void setDefaultString(String defaultString) {
		this.defaultString = defaultString;
	}

	public Boolean getDefaultBool() {
		return defaultBool;
	}

	public void setDefaultBool(Boolean defaultBool) {
		this.defaultBool = defaultBool;
	}

	public Float getDefaultNum() {
		return defaultNum;
	}

	public void setDefaultNum(Float defaultNum) {
		this.defaultNum = defaultNum;
	}

	/**
	 * Возвращает количество голосов за важность данной характеристики
	 * 
	 * @return важность характеристики
	 */
	public Integer getImportanceVotes() {
		return importanceVotes;
	}

	/**
	 * Устанавливает количество голосов за важность данной характеристики
	 *
	 * @param importanceVotes
	 *            важность характеристики
	 */
	public void setImportanceVotes(Integer importanceVotes) {
		this.importanceVotes = importanceVotes;
	}

	/**
	 * {@inheritDoc}
	 */
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((id == null) ? 0 : id.hashCode());
//		result = prime * result + ((inSearch == null) ? 0 : inSearch.hashCode());
//		result = prime * result + ((name == null) ? 0 : name.hashCode());
//		result = prime * result + ((strcode == null) ? 0 : strcode.hashCode());
//		result = prime * result + ((type == null) ? 0 : type.hashCode());
//		result = prime * result + ((orderNum == null) ? 0 : orderNum.hashCode());
//		result = prime * result + ((prompt == null) ? 0 : prompt.hashCode());
//		return result;
//	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Feature other = (Feature) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (inSearch == null) {
			if (other.inSearch != null)
				return false;
		} else if (!inSearch.equals(other.inSearch))
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
//		if (type != other.type)
//			return false;
		if (orderNum == null) {
			if (other.orderNum != null)
				return false;
		} else if (!orderNum.equals(other.orderNum))
			return false;
		if (prompt == null) {
			if (other.prompt != null)
				return false;
		} else if (!prompt.equals(other.prompt))
			return false;
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		String featureGroupStr = "";
		if (featureGroup != null) {
			featureGroupStr = featureGroup.getId().toString();
		}
		return "Feature [id=" + id + ", strcode=" + strcode + ", name=" + name + ", category="
				+ category.getId() + ", featureGroup=" + featureGroupStr + ", inSearch=" + inSearch + ", orderNum="
				+ orderNum + ", prompt=" + prompt + ", hasDefault=" + hasDefault + ", defaultString=" + defaultString
				+ ", defaultBool=" + defaultBool + ", defaultNum=" + defaultNum + ", importanceVotes=" + importanceVotes
				+ "]";
	}

}
