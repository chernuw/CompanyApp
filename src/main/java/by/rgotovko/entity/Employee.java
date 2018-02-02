package by.rgotovko.entity;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "employees", schema = "companydb")
public class Employee implements Serializable{

    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "PHONE", nullable = false, length = 17)
    private String phone;

    @Basic
    @Column(name = "EMAIL", nullable = false, length = 40)
    private String email;

    @Basic
    @Column(name = "FIRST_NAME", nullable = false, length = 15)
    private String firstName;

    @Basic
    @Column(name = "LAST_NAME", nullable = false, length = 20)
    private String lastName;

    @Basic
    @Column(name = "MIDDLE_NAME", length = 20)
    private String middleName;

    @Basic
    @Column(name = "ADDRESS", nullable = false, length = 100)
    private String address;

    @Basic
    @Column(name = "BIRTH_DATE", nullable = false)
    private LocalDate birthDate;

    @Basic
    @Column(name = "PHOTO")
    private byte[] photo;

    @Basic
    @Column(name = "HIRE_DATE")
    private LocalDate hireDate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "dept_emp", joinColumns = {@JoinColumn(name = "EMP_ID")},
            inverseJoinColumns = {@JoinColumn(name = "DEPT_ID")})
    private Department department;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "pos_emp", joinColumns = {@JoinColumn(name = "EMP_ID")},
            inverseJoinColumns = {@JoinColumn(name = "POS_ID")})
    private Position position;

    @OneToOne(/*mappedBy = "employee", */fetch = FetchType.LAZY)
    @JoinColumn(name = "ID", referencedColumnName = "ID", nullable = false)
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) &&
                Objects.equals(phone, employee.phone) &&
                Objects.equals(email, employee.email) &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(middleName, employee.middleName) &&
                Objects.equals(address, employee.address) &&
                Objects.equals(birthDate, employee.birthDate) &&
                Arrays.equals(photo, employee.photo) &&
                Objects.equals(hireDate, employee.hireDate);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(id, phone, email, firstName, lastName, middleName, address, birthDate, hireDate);
        result = 31 * result + Arrays.hashCode(photo);
        return result;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
