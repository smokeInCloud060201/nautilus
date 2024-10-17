package vn.com.lol.nautilus.modules.firstdb.clients.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.lol.nautilus.modules.firstdb.clients.services.ClientService;

import static vn.com.lol.nautilus.commons.constant.ControllerPathConstant.CLIENTS_BASE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/" + CLIENTS_BASE)
public class ClientController {

    private final ClientService clientService;

}
