package com.example.MAMAPhone.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rates")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description", columnDefinition = "text")
    private String description;
    @Column(name = "price")
    private int price;
    @Column(name = "countOfMinutes")
    private int countOfMinutes;
    @Column(name = "countOfTrafficInternet")
    private int countOfTrafficInternet;

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getPrice() {
        return this.price;
    }

    public int getCountOfMinutes() {
        return this.countOfMinutes;
    }

    public int getCountOfTrafficInternet() {
        return this.countOfTrafficInternet;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCountOfMinutes(int countOfMinutes) {
        this.countOfMinutes = countOfMinutes;
    }

    public void setCountOfTrafficInternet(int countOfTrafficInternet) {
        this.countOfTrafficInternet = countOfTrafficInternet;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Rate)) return false;
        final Rate other = (Rate) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        if (this.getPrice() != other.getPrice()) return false;
        if (this.getCountOfMinutes() != other.getCountOfMinutes()) return false;
        if (this.getCountOfTrafficInternet() != other.getCountOfTrafficInternet()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Rate;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        result = result * PRIME + this.getPrice();
        result = result * PRIME + this.getCountOfMinutes();
        result = result * PRIME + this.getCountOfTrafficInternet();
        return result;
    }

    public String toString() {
        return "Rate(id=" + this.getId() + ", name=" + this.getName() + ", description=" + this.getDescription() + ", price=" + this.getPrice() + ", countOfMinutes=" + this.getCountOfMinutes() + ", countOfTrafficInternet=" + this.getCountOfTrafficInternet() + ")";
    }
}
