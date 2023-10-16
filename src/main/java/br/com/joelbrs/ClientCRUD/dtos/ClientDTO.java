package br.com.joelbrs.ClientCRUD.dtos;

import br.com.joelbrs.ClientCRUD.models.Client;
import br.com.joelbrs.ClientCRUD.utils.DateControl;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.Instant;

public class ClientDTO implements Serializable {
    private Long id;
    private String name;
    private String cpf;
    private Double income;
    private Instant birthDate;
    private Integer children;

    public ClientDTO() {}

    public ClientDTO(Long id, String name, String cpf, Double income, Instant birthDate, Integer children) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.birthDate = birthDate;
        this.children = children;
    }

    public ClientDTO(Client client) {
        setId(client.getId());
        setName(client.getName());
        setCpf(client.getCpf());
        setIncome(client.getIncome());
        setBirthDate(client.getBirthDate());
        setChildren(client.getChildren());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateControl.DATE_PATTERN, timezone = "America/Fortaleza")
    public Instant getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Instant birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }
}
