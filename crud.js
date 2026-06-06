document.addEventListener('DOMContentLoaded', function () {
  var gasKey = 'combustisv_gasolineras';
  var precioKey = 'combustisv_precios';

  try {
    var savedGas = JSON.parse(localStorage.getItem(gasKey) || 'null');
    var savedPrecios = JSON.parse(localStorage.getItem(precioKey) || 'null');
    if (Array.isArray(savedGas)) {
      gasolineras.length = 0;
      savedGas.forEach(function (g) { gasolineras.push(g); });
    }
    if (Array.isArray(savedPrecios)) {
      precios.length = 0;
      savedPrecios.forEach(function (p) { precios.push(p); });
    }
  } catch (e) {}

  function saveAll() {
    localStorage.setItem(gasKey, JSON.stringify(gasolineras));
    localStorage.setItem(precioKey, JSON.stringify(precios));
  }

  function nextId(list) {
    if (!list.length) return 1;
    return Math.max.apply(null, list.map(function (x) { return Number(x.id); })) + 1;
  }

  function currentDate() {
    return new Date().toISOString().slice(0, 10);
  }

  function refreshGasSelects() {
    var filtro = document.getElementById('filtro-gas');
    if (filtro) {
      filtro.innerHTML = '<option value="">-- Todas --</option>' + gasolineras.map(function (g) {
        return '<option value="' + g.nombre + '">' + g.nombre + '</option>';
      }).join('');
    }
    var precioGas = document.getElementById('form-precio-gas');
    if (precioGas) {
      precioGas.innerHTML = '<option value="">-- Seleccione --</option>' + gasolineras.map(function (g) {
        return '<option value="' + g.id + '">' + g.nombre + '</option>';
      }).join('');
    }
  }

  function refreshAll() {
    refreshGasSelects();
    renderHomeTable();
    renderPreciosTable(precios);
    renderComparadorTable();
    renderAdminGas();
    renderAdminPrecios();
    var stats = document.querySelectorAll('.admin-stat h2');
    if (stats[0]) stats[0].textContent = gasolineras.length;
    if (stats[1]) stats[1].textContent = precios.length;
  }

  function prepareGasForm() {
    var form = document.getElementById('form-gas');
    if (!form || form.dataset.crudReady) return;
    form.dataset.crudReady = '1';
    var inputs = form.querySelectorAll('input');
    var select = form.querySelector('select');
    inputs[0].id = 'crud_gas_nombre';
    select.id = 'crud_gas_marca';
    inputs[1].id = 'crud_gas_direccion';
    inputs[2].id = 'crud_gas_depto';
    inputs[3].id = 'crud_gas_municipio';
    inputs[4].id = 'crud_gas_telefono';
    var hidden = document.createElement('input');
    hidden.type = 'hidden';
    hidden.id = 'crud_gas_id';
    form.insertBefore(hidden, form.firstChild);
    form.querySelector('.mt-3 button').onclick = saveGas;
  }

  function preparePrecioForm() {
    var form = document.getElementById('form-precio');
    if (!form || form.dataset.crudReady) return;
    form.dataset.crudReady = '1';
    var selects = form.querySelectorAll('select');
    var input = form.querySelector('input[type="number"]');
    selects[1].id = 'crud_precio_tipo';
    input.id = 'crud_precio_valor';
    var hidden = document.createElement('input');
    hidden.type = 'hidden';
    hidden.id = 'crud_precio_id';
    form.insertBefore(hidden, form.firstChild);
    form.querySelector('.mt-3 button').onclick = savePrecio;
  }

  window.renderAdminGas = function () {
    prepareGasForm();
    var tbody = document.querySelector('#tabla-admin-gas tbody');
    if (!tbody) return;
    tbody.innerHTML = gasolineras.map(function (g) {
      return '<tr><td>' + g.id + '</td><td>' + g.nombre + '</td><td>' + g.marca + '</td><td>' + g.direccion + '</td><td>' + g.depto + '</td><td><button class="btn btn-sm" onclick="editGas(' + g.id + ')" style="background:var(--accent);color:#fff;font-size:0.78rem;">Editar</button> <button class="btn btn-sm" onclick="removeGas(' + g.id + ')" style="background:var(--danger);color:#fff;font-size:0.78rem;">Eliminar</button></td></tr>';
    }).join('');
  };

  window.renderAdminPrecios = function () {
    preparePrecioForm();
    refreshGasSelects();
    var tbody = document.querySelector('#tabla-admin-precios tbody');
    if (!tbody) return;
    tbody.innerHTML = precios.map(function (p) {
      return '<tr><td>' + p.id + '</td><td>' + p.gas + '</td><td><span class="badge-tipo">' + p.tipo + '</span></td><td class="fw-bold">$' + Number(p.precio).toFixed(2) + '</td><td>' + p.fecha + '</td><td><button class="btn btn-sm" onclick="editPrecio(' + p.id + ')" style="background:var(--accent);color:#fff;font-size:0.78rem;">Editar</button> <button class="btn btn-sm" onclick="removePrecio(' + p.id + ')" style="background:var(--danger);color:#fff;font-size:0.78rem;">Eliminar</button></td></tr>';
    }).join('');
  };

  window.editGas = function (id) {
    prepareGasForm();
    var g = gasolineras.find(function (x) { return x.id === id; });
    if (!g) return;
    document.getElementById('crud_gas_id').value = g.id;
    document.getElementById('crud_gas_nombre').value = g.nombre;
    document.getElementById('crud_gas_marca').value = g.marca;
    document.getElementById('crud_gas_direccion').value = g.direccion;
    document.getElementById('crud_gas_depto').value = g.depto;
    document.getElementById('crud_gas_municipio').value = g.municipio || '';
    document.getElementById('crud_gas_telefono').value = g.telefono || '';
    document.querySelector('#form-gas h5').textContent = 'Editar Gasolinera';
    document.getElementById('form-gas').style.display = 'block';
  };

  window.removeGas = function (id) {
    if (!confirm('Eliminar esta gasolinera tambien eliminara sus precios asociados.')) return;
    for (var i = gasolineras.length - 1; i >= 0; i--) if (gasolineras[i].id === id) gasolineras.splice(i, 1);
    for (var j = precios.length - 1; j >= 0; j--) if (precios[j].gasId === id) precios.splice(j, 1);
    saveAll();
    refreshAll();
  };

  function saveGas() {
    var id = document.getElementById('crud_gas_id').value;
    var nombre = document.getElementById('crud_gas_nombre').value.trim();
    var marca = document.getElementById('crud_gas_marca').value;
    var direccion = document.getElementById('crud_gas_direccion').value.trim();
    var depto = document.getElementById('crud_gas_depto').value.trim();
    var municipio = document.getElementById('crud_gas_municipio').value.trim();
    var telefono = document.getElementById('crud_gas_telefono').value.trim();
    if (!nombre || !direccion || !depto || !municipio) { alert('Complete los campos requeridos.'); return; }
    if (id) {
      var g = gasolineras.find(function (x) { return x.id === Number(id); });
      g.nombre = nombre; g.marca = marca; g.direccion = direccion; g.depto = depto; g.municipio = municipio; g.telefono = telefono;
      precios.forEach(function (p) { if (p.gasId === g.id) { p.gas = nombre; p.marca = marca; } });
    } else {
      gasolineras.push({ id: nextId(gasolineras), nombre: nombre, marca: marca, direccion: direccion, depto: depto, municipio: municipio, telefono: telefono });
    }
    saveAll();
    document.getElementById('form-gas').style.display = 'none';
    document.querySelector('#form-gas h5').textContent = 'Nueva Gasolinera';
    refreshAll();
  }

  window.editPrecio = function (id) {
    preparePrecioForm();
    refreshGasSelects();
    var p = precios.find(function (x) { return x.id === id; });
    if (!p) return;
    document.getElementById('crud_precio_id').value = p.id;
    document.getElementById('form-precio-gas').value = p.gasId;
    document.getElementById('crud_precio_tipo').value = p.tipo;
    document.getElementById('crud_precio_valor').value = p.precio;
    document.querySelector('#form-precio h5').textContent = 'Editar Precio';
    document.getElementById('form-precio').style.display = 'block';
  };

  window.removePrecio = function (id) {
    if (!confirm('Eliminar este precio?')) return;
    for (var i = precios.length - 1; i >= 0; i--) if (precios[i].id === id) precios.splice(i, 1);
    saveAll();
    refreshAll();
  };

  function savePrecio() {
    var id = document.getElementById('crud_precio_id').value;
    var gasId = Number(document.getElementById('form-precio-gas').value);
    var tipo = document.getElementById('crud_precio_tipo').value;
    var precio = Number(document.getElementById('crud_precio_valor').value);
    var gas = gasolineras.find(function (g) { return g.id === gasId; });
    if (!gas || !precio || precio <= 0) { alert('Seleccione gasolinera y precio valido.'); return; }
    if (id) {
      var p = precios.find(function (x) { return x.id === Number(id); });
      p.gasId = gas.id; p.gas = gas.nombre; p.marca = gas.marca; p.tipo = tipo; p.precio = precio; p.fecha = currentDate();
    } else {
      precios.push({ id: nextId(precios), gasId: gas.id, gas: gas.nombre, marca: gas.marca, tipo: tipo, precio: precio, fecha: currentDate() });
    }
    saveAll();
    document.getElementById('form-precio').style.display = 'none';
    document.querySelector('#form-precio h5').textContent = 'Registrar Nuevo Precio';
    refreshAll();
  }

  prepareGasForm();
  preparePrecioForm();
  refreshAll();
});
