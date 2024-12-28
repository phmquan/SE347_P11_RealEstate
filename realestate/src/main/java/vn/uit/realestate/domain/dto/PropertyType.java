package vn.uit.realestate.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum PropertyType {
  HOUSE,
  APARTMENT,
  LAND,
  ;

  @JsonCreator
  public static PropertyType fromString(String type) {
    for (PropertyType propertyType : PropertyType.values()) {
      if (propertyType.name().equalsIgnoreCase(type)) {
        return propertyType;
      }
    }
    return null;
  }
}
