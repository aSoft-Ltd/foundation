config.resolve.modules.push("/media/andylamax/workspace/aSoft/OSS/foundation/code/foundation-samples/applikation-samples/browser-applikation/build/resources/main")
config.module.rules.push({
    test: /\.(png|jpe?g|gif|svg)$/i,
    use: [
      {
        loader: 'file-loader',
      },
    ],
});
config.devServer = { ...config.devServer, historyApiFallback: true, host: "0.0.0.0" }