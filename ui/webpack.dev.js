const path = require("path");
const webpack = require("webpack");

const merge = require('webpack-merge');
const common = require('./webpack.common.js');

module.exports = merge(common, {
  mode: 'development',
//  module:{
//    rules: [
//       {
//         enforce: "pre",
//         test: /\.tsx$/,
//         loader: "eslint-loader"
//       }
//    ]
//  },
  devServer: {
    contentBase: path.join(__dirname, "public/"),
    port: 5000,
    publicPath: "http://localhost:5000/dist/",
    hotOnly: false,
    proxy: {
      '/products/**': 'http://localhost:8080/',
      '/analysis/**': 'http://localhost:8084/'
    }
  },
  plugins: [
    new webpack.HotModuleReplacementPlugin()
  ]
});
