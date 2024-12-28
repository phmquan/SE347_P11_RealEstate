package vn.uit.realestate.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.uit.realestate.domain.Property;
import vn.uit.realestate.repository.PropertyRepository;

@Service
@RequiredArgsConstructor
public class PropertyService {

  private final PropertyRepository propertyRepository;

  public List<Property> getAllProperties() {
    return propertyRepository.findAll();
  }

  public Optional<Property> getPropertyById(Long id) {
    return propertyRepository.findById(id);
  }

  public Property addProperty(Property property) {
    return propertyRepository.save(property);
  }

  public Property updateProperty(Long id, Property updatedProperty) {
    return propertyRepository
        .findById(id)
        .map(
            property -> {
              property.setAddress(updatedProperty.getAddress());
              property.setPropertyLength(updatedProperty.getPropertyLength());
              property.setPropertyWidth(updatedProperty.getPropertyWidth());
              property.setPropertyArea(updatedProperty.getPropertyArea());
              property.setLegalDocuments(updatedProperty.getLegalDocuments());
              property.setPropertyPrice(updatedProperty.getPropertyPrice());
              property.setPropertyImages(updatedProperty.getPropertyImages());
              return propertyRepository.save(property);
            })
        .orElseThrow(() -> new RuntimeException("Property not found with id: " + id));
  }

  public void deleteProperty(Long id) {
    propertyRepository.deleteById(id);
  }
}
