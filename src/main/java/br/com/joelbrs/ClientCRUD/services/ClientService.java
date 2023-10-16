package br.com.joelbrs.ClientCRUD.services;

import br.com.joelbrs.ClientCRUD.dtos.ClientDTO;
import br.com.joelbrs.ClientCRUD.models.Client;
import br.com.joelbrs.ClientCRUD.repositories.ClientRepository;
import br.com.joelbrs.ClientCRUD.services.exceptions.ResourceAlreadyExistsException;
import br.com.joelbrs.ClientCRUD.services.exceptions.ResourceDoesNotExistsException;
import br.com.joelbrs.ClientCRUD.services.exceptions.ResourceFieldsShouldNotBeEmptyException;
import br.com.joelbrs.ClientCRUD.services.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class ClientService {
    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable) {
        Page<Client> clients =  repository.findAll(pageable);

        return clients.map(ClientDTO::new);
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Client client = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found, ID: " + id));

        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto) {
        Boolean clientExists = repository.findByCpf(dto.getCpf()).isPresent();

        if (clientExists) {
            throw new ResourceAlreadyExistsException("CPF already exists!");
        }

        if (Objects.isNull(dto.getCpf())) {
            throw new ResourceFieldsShouldNotBeEmptyException("CPF field should not be empty (null)!");
        }

        if (Objects.isNull(dto.getName())) {
            throw new ResourceFieldsShouldNotBeEmptyException("Name field should not be empty (null)!");
        }

        Client client = new Client(null, dto.getName(), dto.getCpf(), dto.getIncome(), dto.getBirthDate(), dto.getChildren());
        return new ClientDTO(repository.save(client));
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto) {
        Client client = repository.findById(id).orElseThrow(() -> new ResourceDoesNotExistsException("Client doesn't exists!"));

        client.setChildren(dto.getChildren());
        client.setIncome(dto.getIncome());

        return new ClientDTO(repository.save(client));
    }

    public void delete(Long id) {
        Client client = repository.findById(id).orElseThrow(() -> new ResourceDoesNotExistsException("Client doesn't exists!"));

        repository.deleteById(id);
    }
}
