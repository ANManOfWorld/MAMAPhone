package com.example.MAMAPhone.models;

import com.example.MAMAPhone.models.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Entity // класс является сущностью и будет сохраняться в БД
@Table(name = "users")
//аннотация сгенирует при компиляции необходимый код от LOMBOK
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email", unique = true)
    @NotEmpty(message = "Email должен быть заполнен.") //validator
    @Email(message = "Email должен быть валидным.", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @Column(name = "active")
    private boolean active;

    @Column(name = "password", length = 1024)
    @NotEmpty(message = "Пароль должен быть введён.") //validator
    private String password;

    @Column(name = "phoneNum")
    @NotEmpty(message = "Телефон должен быть введён.") //validator
    private String phoneNum;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_Role", joinColumns = @JoinColumn(name = "user_Id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    private LocalDateTime dateOfCreated;

    @Column(name = "name")
    @NotEmpty(message = "ФИО должно быть заполнено") //validator
    @Size(min = 2, max = 32, message = "Имя должно быть не менее 2 символов и не более 32")
    private String name;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "image_Id")
    private Image imageOfUser;

    public User(Long id, @NotEmpty @Email(message = "Email должен быть валидным.") String email, boolean active, @NotEmpty String password, @NotEmpty(message = "Телефон должен быть введён корректно.") String phoneNum, Set<Role> roles, LocalDateTime dateOfCreated, @NotEmpty(message = "ФИО должно быть заполнено") @Size(min = 2, max = 32, message = "Имя должно быть не менее 2 символов и не более 32") String name, Image imageOfUser) {
        this.id = id;
        this.email = email;
        this.active = active;
        this.password = password;
        this.phoneNum = phoneNum;
        this.roles = roles;
        this.dateOfCreated = dateOfCreated;
        this.name = name;
        this.imageOfUser = imageOfUser;
    }

    public User() {
    }

    //Дата создания аккаунта
    @PrePersist
    private void init() {
        dateOfCreated = LocalDateTime.now();
    }

    //понятие РОЛИ
    public boolean isAdmin() {
        return roles.contains(Role.ROLE_ADMIN);
    }

    public boolean isUser() {
        return roles.contains(Role.ROLE_USER);
    }


    //отимплементированные методы
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }





    public Long getId() {
        return this.id;
    }

    public @NotEmpty @Email(message = "Email должен быть валидным.") String getEmail() {
        return this.email;
    }

    public boolean isActive() {
        return this.active;
    }

    public @NotEmpty String getPassword() {
        return this.password;
    }

    public @NotEmpty(message = "Телефон должен быть введён корректно.") String getPhoneNum() {
        return this.phoneNum;
    }

    public Set<Role> getRoles() {
        return this.roles;
    }

    public LocalDateTime getDateOfCreated() {
        return this.dateOfCreated;
    }

    public @NotEmpty(message = "ФИО должно быть заполнено") @Size(min = 2, max = 32, message = "Имя должно быть не менее 2 символов и не более 32") String getName() {
        return this.name;
    }

    public Image getImageOfUser() {
        return this.imageOfUser;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(@NotEmpty @Email(message = "Email должен быть валидным.") String email) {
        this.email = email;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setPassword(@NotEmpty String password) {
        this.password = password;
    }

    public void setPhoneNum(@NotEmpty(message = "Телефон должен быть введён корректно.") String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setDateOfCreated(LocalDateTime dateOfCreated) {
        this.dateOfCreated = dateOfCreated;
    }

    public void setName(@NotEmpty(message = "ФИО должно быть заполнено") @Size(min = 2, max = 32, message = "Имя должно быть не менее 2 символов и не более 32") String name) {
        this.name = name;
    }

    public void setImageOfUser(Image imageOfUser) {
        this.imageOfUser = imageOfUser;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof User)) return false;
        final User other = (User) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
        if (this.isActive() != other.isActive()) return false;
        final Object this$password = this.getPassword();
        final Object other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
        final Object this$phoneNum = this.getPhoneNum();
        final Object other$phoneNum = other.getPhoneNum();
        if (this$phoneNum == null ? other$phoneNum != null : !this$phoneNum.equals(other$phoneNum)) return false;
        final Object this$roles = this.getRoles();
        final Object other$roles = other.getRoles();
        if (this$roles == null ? other$roles != null : !this$roles.equals(other$roles)) return false;
        final Object this$dateOfCreated = this.getDateOfCreated();
        final Object other$dateOfCreated = other.getDateOfCreated();
        if (this$dateOfCreated == null ? other$dateOfCreated != null : !this$dateOfCreated.equals(other$dateOfCreated))
            return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$imageOfUser = this.getImageOfUser();
        final Object other$imageOfUser = other.getImageOfUser();
        if (this$imageOfUser == null ? other$imageOfUser != null : !this$imageOfUser.equals(other$imageOfUser))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof User;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        result = result * PRIME + (this.isActive() ? 79 : 97);
        final Object $password = this.getPassword();
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        final Object $phoneNum = this.getPhoneNum();
        result = result * PRIME + ($phoneNum == null ? 43 : $phoneNum.hashCode());
        final Object $roles = this.getRoles();
        result = result * PRIME + ($roles == null ? 43 : $roles.hashCode());
        final Object $dateOfCreated = this.getDateOfCreated();
        result = result * PRIME + ($dateOfCreated == null ? 43 : $dateOfCreated.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $imageOfUser = this.getImageOfUser();
        result = result * PRIME + ($imageOfUser == null ? 43 : $imageOfUser.hashCode());
        return result;
    }

    public String toString() {
        return "User(id=" + this.getId() + ", email=" + this.getEmail() + ", active=" + this.isActive() + ", password=" + this.getPassword() + ", phoneNum=" + this.getPhoneNum() + ", roles=" + this.getRoles() + ", dateOfCreated=" + this.getDateOfCreated() + ", name=" + this.getName() + ", imageOfUser=" + this.getImageOfUser() + ")";
    }
}
