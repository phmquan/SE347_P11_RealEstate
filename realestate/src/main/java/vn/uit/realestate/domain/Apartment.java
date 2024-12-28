package vn.uit.realestate.domain;

import jakarta.validation.constraints.NotBlank;

public class Apartment extends Property {

  private String apartmentCode; // Mã căn hộ
  private String apartmentFloor; // Tầng
  private String apartmentBlock;

  // Căn hộ chung cư, căn hộ officetel, căn hộ duplex, căn hộ penthouse
  @NotBlank(message = "Loại căn hộ không được để trống")
  private String apartmentType;

  private double apartmentRoom; // Số phòng ngủ
  private double apartmentToilet; // Số phòng vệ sinh
  private String apartmentDirection; // Hướng căn hộ
}
