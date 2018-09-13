const VueLoaderPlugin = require('vue-loader/lib/plugin')

module.exports = {
  module: {
    rules: [
      { 
        test: /\.vue$/, 
        loader: 'vue-loader'
      },

      {
        test: /\.css$/,
        use: [
          {
            loader: 'vue-style-loader'
          },
          {
            loader: 'css-loader',
          }
        ]
      }
    ]
  }, 
  plugins: [
    new VueLoaderPlugin()
  ]
};
