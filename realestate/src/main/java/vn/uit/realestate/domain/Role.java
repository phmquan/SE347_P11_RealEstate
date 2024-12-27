package vn.uit.realestate.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String name;
  private String description;

  @OneToMany(mappedBy = "role")
  private List<User> users;

  public Role(String name) {
    this.name = name;
    this.description = "Role for " + name;
  }

  @Override
  public String toString() {
    return "roles [id=" + id + ", name=" + name + ", description=" + description + "]";
  }
}
