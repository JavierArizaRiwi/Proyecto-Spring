package com.riwitienda.pagos.services.impl;

import com.riwitienda.pagos.web.dto.UsuarioRequest;
import com.riwitienda.pagos.web.dto.UsuarioResponse;

import java.util.List;

public interface IUsuarioService {

        UsuarioResponse crear(UsuarioRequest req);
        UsuarioResponse obtenerPorId(String id);
        List<UsuarioResponse> listar();
}
