package org.example.models;

import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Категория
 */
@Entity
@Table(name = Category.TABLE_NAME)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String TABLE_NAME = "sp_category";

	/** Идентификатор */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 50, nullable = false)
	private String strcode;

	/** Наименование */
	@Column(nullable = false)
	private String name;

	/** Описание */
	@Column(columnDefinition = "longtext")
	private String description;

	@ManyToOne(targetEntity = Category.class, optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Category parent;

	@OneToMany(targetEntity = Category.class, mappedBy = "parent", fetch = FetchType.LAZY)
	@OrderBy("totalVisits DESC")
	private List<Category> children;

	@OneToMany(targetEntity = Feature.class, mappedBy = "category", fetch = FetchType.LAZY)
	@OrderBy("order_num")
	private List<Feature> features;

	@OneToMany(targetEntity = FeatureGroup.class, mappedBy = "category", fetch = FetchType.LAZY)
	@OrderBy("order_num")
	private List<FeatureGroup> featureGroups;

	@Column(name = "page_title")
	private String pageTitle;

	@Column(name = "page_description", columnDefinition = "longtext")
	private String pageDescription;

	@Column(name = "page_keywords", length = 1000)
	private String pageKeywords;

	@Column(name = "apps_count")
	private Integer appsCount;

	@Column(nullable = true)
	private Integer priority;

	@Column(name = "name_synonyms")
	private String nameSynonyms;

	@Column(nullable = true)
	private Integer totalVisits;

	@Column(name = "discussion_count", nullable = false)
	@ColumnDefault("0")
	private Integer discussionCount = 0;

	/**
	 * Возвращает идентификатор
	 * 
	 * @return идентификатор
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Устанавливает идентификатор
	 * 
	 * @param id
	 *            идентификатор
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public String getStrcode() {
		return strcode;
	}

	public void setStrcode(String strcode) {
		this.strcode = strcode;
	}

	/**
	 * Возвращает наименование
	 * 
	 * @return наименование
	 */
	public String getName() {
		return name;
	}

	/**
	 * Устанавливает наименование
	 * 
	 * @param name
	 *            наименование
	 */
	public void setName(String name) {
		this.name = name;
	}

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	public List<Category> getParentPath() {
		final List<Category> result = new ArrayList<Category>();
		Category p = getParent();
		while (p != null) {
			result.add(0, p);
			p = p.getParent();
		}
		return result;
	}

	public List<Category> getChildren() {
		if (children == null)
			children = new ArrayList<Category>();
		return children;
	}

	public void setChildren(List<Category> children) {
		this.children = children;
	}

	public List<Feature> getFeatures() {
		if (features == null)
			features = new ArrayList<Feature>();
		return features;
	}

	public void setFeatures(List<Feature> features) {
		this.features = features;
	}

	public List<FeatureGroup> getFeatureGroups() {
		return featureGroups;
	}

	public void setFeatureGroups(List<FeatureGroup> featureGroups) {
		this.featureGroups = featureGroups;
	}

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	public String getPageDescription() {
		return pageDescription;
	}

	public void setPageDescription(String pageDescription) {
		this.pageDescription = pageDescription;
	}

	public String getPageKeywords() {
		return pageKeywords;
	}

	public void setPageKeywords(String pageKeywords) {
		this.pageKeywords = pageKeywords;
	}

	public Integer getAppsCount() {
		return appsCount == null ? 0 : appsCount;
	}

	public void setAppsCount(Integer appsCount) {
		this.appsCount = appsCount;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getNameSynonyms() {
		return nameSynonyms;
	}

	public void setNameSynonyms(String nameSynonyms) {
		this.nameSynonyms = nameSynonyms;
	}

	public Integer getTotalVisits() {
		return totalVisits == null ? 0 : totalVisits;
	}

	public void setTotalVisits(Integer totalVisits) {
		this.totalVisits = totalVisits;
	}

	public Integer getDiscussionCount() {
		return discussionCount;
	}

	public void setDiscussionCount(Integer discussionCount) {
		this.discussionCount = discussionCount;
	}

	/**
	 * Возвращает описание
	 * 
	 * @return description описание
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Устанавливает описание
	 *
	 * @param description
	 *            описание
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((strcode == null) ? 0 : strcode.hashCode());
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
		final Category other = (Category) obj;
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
		return true;
	}

	private String getChildrenList() {
		if (children == null || children.isEmpty())
			return "";
		StringBuilder sb = new StringBuilder("[");
		for (Category cat : children)
			sb.append(cat.getStrcode()).append(",");
		sb.append("]");
		return sb.toString();
	}

	private String getFeaturesList() {
		if (features == null || features.isEmpty())
			return "";
		StringBuilder sb = new StringBuilder("[");
		for (Feature f : features)
			sb.append(f.getStrcode()).append(",");
		sb.append("]");
		return sb.toString();
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", strcode=" + strcode + ", name=" + name + ", parent=" + parent.getStrcode()
				+ ", priority=" + priority + ", children=" + getChildrenList() + ", features=" + getFeaturesList()
				+ ", pageTitle=" + pageTitle + ", pageDescription=" + pageDescription + ", pageKeywords=" + pageKeywords
				+ ", nameSynonyms=" + nameSynonyms + "]";
	}

}
