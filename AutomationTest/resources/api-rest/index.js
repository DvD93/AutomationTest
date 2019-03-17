'use strict'
const express = require('express')
const bodyParser = require('body-parser')
const app = express()
const port = process.env.PORT || 3000

var products = [{ "id": 1, "nombre": "monitor", "cantidad": 12 },
    { "id": 2, "nombre": "CPU", "cantidad": 3 }, { "id": 3, "nombre": "teclado", "cantidad": 20 }
];

var insumos = [{ "id": 1, "nombre": "tinta 952", "cantidad": 6 },
    { "id": 2, "nombre": "papel A4", "cantidad": 5 }
]

function getProd(prodID) {
    console.log("getProd", prodID)
    return products.find(
        function(prod) {
            return prod.id == prodID;
        }
    )
}

function getProdByName(prodName) {
    return products.find(
        function(prod) {
            return prod.nombre == prodName;
        }
    )
}

function getIns(insId) {
    return insumos.find(
        function(ins) {
            return ins.id == insId;
        }
    )
}

function getInsByName(insNombre) {
    return insumos.find(
        function(ins) {
            return ins.nombre == insNombre;
        }
    )
}

app.use(bodyParser.urlencoded({ extended: false }))
app.use(bodyParser.json())

//queryParam
//  http://localhost:3000/api/product?id=1
app.get('/api/product', (req, res) => {
    if (req.query.productId) {
        var result = getProd(req.query.productId);
        if (result) {
            res.status(200).send(result)
        } else {
            res.status(200).send("el producto no fue encontrado");
        }
    } else if (req.query.name) {
        var result = getProdByName(req.query.name);
        res.status(200).send(result)
    } else {
        res.status(200).send({ products: products })
    }
})

//pathParam
//  http://localhost:3000/api/product/1
app.get('/api/product/:id', (req, res) => {
    console.log(req.params)
    var result = getProd(req.params["id"]);
    if (result) {
        res.status(200).send(result);
    } else {
        res.status(204).send();
    }
})

app.post('/api/product', (req, res) => {
    console.log(req.body)
    if (req.body && req.body.id != null) {
        products.push(req.body);
        res.status(201).send({ message: 'El producto se ha recibido' });
    } else {
        res.status(400).send({ message: 'bad Request' });
    }

})

app.put('/api/product/:productId', (req, res) => {})

function getObjectIndexByKey(array, key, value) {
    for (var i = 0; i < array.length; i++) {
        if (array[i][key] == value) {
            return i;
        }
    }
    return null;
}

app.delete('/api/product/:productId', (req, res) => {

    var index = getObjectIndexByKey(products, "id", req.params.productId);

    if (index !== null) {
        products.splice(index, 1);
        res.status(200).send({ message: 'El producto ha sido eliminado exitosamente.' });
    } else {
        res.status(200).send({ message: 'El producto no fue encontrado.' });

    };
})



//queryParam, ex. http://localhost:3000/api/insumo?id=1
app.get('/api/insumo', (req, res) => {
    if (req.query.id) {
        var result = getIns(req.query.id);
        if (result) {
            res.status(200).send(result)
        } else {
            res.status(200).send({ mensaje: 'Insumo no encontrado' })
        }
    } else if (req.query.nombre) {
        var result = getInsByName(req.query.nombre);
        if (result) {
            res.status(200).send(result)
        } else {
            res.status(200).send({ mensaje: 'Insumo no encontrado' })
        }
    } else {
        res.status(200).send({ insumos: insumos })
    }
})

//pathParam en insumos por Nombre, ej. http://localhost:3000/api/insumo/tinta 952
app.get('/api/insumo/:nombre', (req, res) => {
    var result = getIns(req.params.nombre);
    if (result) {
        res.status(200).send(result)
    } else {
        res.status(200).send({ mensaje: 'Insumo no encontrado' })
    }
})

//pathParam
//  http://localhost:3000/api/insumo/1
app.get('/api/insumo/:id', (req, res) => {
    var result = getIns(req.params.id);
    if (result) {
        res.status(200).send(result)
    } else {
        res.status(200).send({ mensaje: 'Insumo no encontrado' })
    }
})
app.post('/api/insumo', (req, res) => {
    console.log(req.body)
    if (req.body && req.body.id != null) {
        insumos.push(req.body);
        res.status(201).send({ message: 'El insumo se ha agregado' });
    } else {
        res.status(400).send({ message: 'bad Request' });
    }

})
app.put('/api/insumo', (req, res) => {})


app.delete('/api/insumo/:id', (req, res) => {

    var index = getObjectIndexByKey(insumos, "id", req.params.id);

    if (index !== null) {
        insumos.splice(index, 1);
        res.status(200).send({ message: 'El insumo ha sido eliminado exitosamente.' });
    } else {
        res.status(200).send({ message: 'El insumo no fue encontrado.' });

    };
})

app.listen(port, () => {
    console.log(`API REST corriendo en http://localhost:${port}`)
})
