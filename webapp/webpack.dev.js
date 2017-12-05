const merge = require('webpack-merge');
const common = require('./webpack.common.js');

module.exports = merge(common,{
    
  devtool:'source-map',
  devServer: {
	    proxy: {
	      '/api': 'http://127.0.0.1:4567'
	    }
  }
    
});
