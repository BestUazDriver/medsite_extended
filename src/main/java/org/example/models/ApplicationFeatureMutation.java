package org.example.models;

import javax.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = ApplicationFeatureMutation.TABLE_NAME)
public class ApplicationFeatureMutation implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String TABLE_NAME = "sp_application_feature_mutation";

	@Id
	@Column(name = "application_id")
	private Long applicationId;

	@Id
	@ManyToOne(targetEntity = Feature.class, fetch = FetchType.EAGER, cascade = { CascadeType.REMOVE })
	@JoinColumn(name = "feature_id")
	private Feature feature;

	@Column(name = "str_value")
	private String strValue;

	@Column(name = "bool_value")
	private Boolean boolValue;

	@Column(name = "num_value")
	private Float numValue;

//	@ManyToOne(targetEntity = FeatureEnum.class, fetch = FetchType.LAZY)
//	@JoinColumn(name = "enum_value")
//	private FeatureEnum enumValue;
//
//	@ManyToMany(targetEntity = FeatureEnum.class, fetch = FetchType.LAZY)
//	@JoinTable(name = "sp_feature_enum_checked_mutation", joinColumns = {
//			@JoinColumn(name = "application_id", referencedColumnName = "application_id"),
//			@JoinColumn(name = "feature_id", referencedColumnName = "feature_id") }, inverseJoinColumns = { @JoinColumn(name = "feature_enum_id") })
//	private List<FeatureEnum> multValue;

	@Transient
	private List<String> multValueCodes;

	public ApplicationFeatureMutation() {
	}



//	public ApplicationFeatureMutation(Long appId, ApplicationFeature appFeature) {
//		this();
//		this.applicationId = appId;
//		this.feature = appFeature.getFeature();
//		this.strValue = appFeature.getStrValue();
//		this.boolValue = appFeature.getBoolValue();
//		this.numValue = appFeature.getNumValue();
//		this.enumValue = appFeature.getEnumValue();
//	}
//
//	public ApplicationFeatureMutation(Long appId, ApplicationFeatureDeleted afDel) {
//		this();
//		this.applicationId = appId;
//		this.feature = afDel.getFeature();
//		this.strValue = afDel.getStrValue();
//		this.boolValue = afDel.getBoolValue();
//		this.numValue = afDel.getNumValue();
//		this.enumValue = afDel.getEnumValue();
//		copyMultValue(afDel.getMultValue());
//	}
//
//	@Override
//	public Long getApplicationId() {
//		return applicationId;
//	}
//
//	@Override
//	public void setApplicationId(Long applicationId) {
//		this.applicationId = applicationId;
//	}
//
//	@Override
//	public Feature getFeature() {
//		return feature;
//	}
//
//	@Override
//	public void setFeature(Feature feature) {
//		this.feature = feature;
//	}
//
//	@Override
//	public String getStrValue() {
//		return strValue;
//	}
//
//	@Override
//	public void setStrValue(String value) {
//		this.strValue = value;
//	}
//
//	@Override
//	public Boolean getBoolValue() {
//		return boolValue;
//	}
//
//	@Override
//	public void setBoolValue(Boolean value) {
//		this.boolValue = value;
//	}
//
//	@Override
//	public Float getNumValue() {
//		return numValue;
//	}
//
//	@Override
//	public void setNumValue(Float value) {
//		this.numValue = value;
//	}
//
//	@Override
//	public void setNumValueAsString(String value) {
//		if (MAX_NUM_SYMBOL.equals(value))
//			this.numValue = MAX_NUM_VALUE;
//		else
//			this.numValue = DBUtils.getFloatFromString(value);
//	}
//
//	@Override
//	public FeatureEnum getEnumValue() {
//		return enumValue;
//	}
//
//	@Override
//	public void setEnumValue(FeatureEnum enumValue) {
//		this.enumValue = enumValue;
//	}
//
//	@Override
//	public List<FeatureEnum> getMultValue() {
//		return multValue;
//	}
//
//	@Override
//	public void setMultValue(List<FeatureEnum> multValue) {
//		this.multValue = multValue;
//	}
//
//	public void copyMultValue(List<FeatureEnum> multValue) {
//		this.multValue = new ArrayList<FeatureEnum>(multValue.size());
//		for (FeatureEnum fe : multValue)
//			this.multValue.add(fe);
//	}
//
//	@Override
//	public List<String> getMultValueCodes() {
//		if (multValueCodes == null) {
//			if (multValue != null && !multValue.isEmpty()) {
//				multValueCodes = new ArrayList<String>(multValue.size());
//				for (FeatureEnum fe : multValue)
//					multValueCodes.add(fe.getStrcode());
//			} else
//				multValueCodes = Collections.emptyList();
//		}
//		return multValueCodes;
//	}
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((applicationId == null) ? 0 : applicationId.hashCode());
//		result = prime * result + ((boolValue == null) ? 0 : boolValue.hashCode());
//		result = prime * result + ((enumValue == null) ? 0 : enumValue.hashCode());
//		result = prime * result + ((feature == null) ? 0 : feature.hashCode());
//		result = prime * result + ((multValue == null) ? 0 : multValue.hashCode());
//		result = prime * result + ((multValueCodes == null) ? 0 : multValueCodes.hashCode());
//		result = prime * result + ((numValue == null) ? 0 : numValue.hashCode());
//		result = prime * result + ((strValue == null) ? 0 : strValue.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		ApplicationFeatureMutation other = (ApplicationFeatureMutation) obj;
//		if (applicationId == null) {
//			if (other.applicationId != null)
//				return false;
//		} else if (!applicationId.equals(other.applicationId))
//			return false;
//		if (boolValue == null) {
//			if (other.boolValue != null)
//				return false;
//		} else if (!boolValue.equals(other.boolValue))
//			return false;
//		if (enumValue == null) {
//			if (other.enumValue != null)
//				return false;
//		} else if (!enumValue.equals(other.enumValue))
//			return false;
//		if (feature == null) {
//			if (other.feature != null)
//				return false;
//		} else if (!feature.equals(other.feature))
//			return false;
//		if (multValue == null) {
//			if (other.multValue != null)
//				return false;
//		} else if (!multValue.equals(other.multValue))
//			return false;
//		if (numValue == null) {
//			if (other.numValue != null)
//				return false;
//		} else if (!numValue.equals(other.numValue))
//			return false;
//		if (strValue == null) {
//			if (other.strValue != null)
//				return false;
//		} else if (!strValue.equals(other.strValue))
//			return false;
//		return true;
//	}
//
	@Override
	public String toString() {
		return "ApplicationFeatureMutation [applicationId=" + applicationId + ", feature=" + feature + ", strValue="
				+ strValue + ", boolValue=" + boolValue + ", numValue=" + numValue + "]";
	}
//
//	public boolean isMaxNumValue() {
//		return MAX_NUM_VALUE.equals(this.numValue);
//	}
//
//	@Override
//	public String getMaxNumSymbol() {
//		return MAX_NUM_SYMBOL;
//	}
//
//	@Override
//	public boolean isUnlimited() {
//		return MAX_NUM_VALUE.equals(this.numValue);
//	}
//
//	@Override
//	public boolean isSelectableValue() {
//		return false;
//	}

}
