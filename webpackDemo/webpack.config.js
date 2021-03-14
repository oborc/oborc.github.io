'use strict'

const path = require('path');

module.exports = {
    entry: {
        index: "./src/index.js",
        entry2: "./src/entry2.js"
    },
    output: {
        path: path.join(__dirname,'dist'),
        filename: '[name].js'
    },
    mode: 'production'
}