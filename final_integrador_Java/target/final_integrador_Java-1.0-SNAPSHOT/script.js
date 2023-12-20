const precio = 200;
const cantidad = document.getElementById('cantidad');
const categoria = document.getElementById('categoria');
const costo = document.getElementById('costo');

function resumen(){
    if (cantidad.value >= 0){
        let valor = cantidad.value * precio;
        let final = valor - (valor*categoria.value);
        costo.innerText = final;
    }else{
        alert("la cantidad es menor a 0");
    }
}

function borrar(){
    cantidad.value = 0;
    costo.innerText = 0;
}