const path = require('path')
const HtmlWebpackPlugin = require('html-webpack-plugin')
// const CopyWebpack = require('copy-webpack-plugin')


module.exports = {
  entry: path.resolve(__dirname, '..', './src/index.tsx'),
  resolve: {
        extensions: ['.js', '.ts', '.tsx'],
    },
    output: {
      path: path.resolve(__dirname, '..', './build'),
      filename: '[name].[chunkhash].js',
  }, 
  module: {
    rules: [
         {
         // should use babel-loader for all ts js tsx and jsx files
            test: /\.(ts|js)x?$/,
            exclude: /node_modules/,
            use: [
               {
                 loader: 'babel-loader',
               },
            ],
         },
    ],
  },
  plugins: [
    new HtmlWebpackPlugin({
      template: path.resolve(__dirname, "..", "./src/index.html")
    }),
    // new CopyWebpack({
    //   patterns: [{ from: 'sourcefolder', to: 'destinationfolder' }],
    // }),
  ],
}
