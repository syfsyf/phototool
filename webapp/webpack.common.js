var webpack = require('webpack');

module.exports = {
  entry: './src/js/app.jsx',
  output: {
    path: __dirname+'/dist',
    filename: 'bundle.js'
  },
  module: {
    loaders: [
      {
        test: /\.js$|\.jsx$/,
        exclude: /node_modules/,
        loader: 'babel-loader',
        query: { presets: [ 'es2015', 'react' ] }
      }      
    ]
  },
  resolve: {
	    extensions: [ '.js', '.jsx']
  }
  
  
  
  /*,
  plugins:[
	new webpack.optimize.UglifyJsPlugin({
    compress: { warnings: false }
  })   
  ]*/
};
