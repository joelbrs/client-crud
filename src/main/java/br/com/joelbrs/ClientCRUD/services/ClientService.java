package br.com.joelbrs.ClientCRUD.services;

import br.com.joelbrs.ClientCRUD.dtos.ClientDTO;
import br.com.joelbrs.ClientCRUD.models.Client;
import br.com.joelbrs.ClientCRUD.repositories.ClientRepository;
import br.com.joelbrs.ClientCRUD.services.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
}
