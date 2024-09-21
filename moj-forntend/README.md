# moj-forntend

## Project setup

```
npm install
```

### Compiles and hot-reloads for development

```
npm run serve
```

```
openapi --input http://localhost:8121/api/v2/api-docs --output ./generated --client axios
```

```angular2html
# Judge 服务
openapi --input http://localhost:8101/api/judge/v2/api-docs --output  ./generated/judge --client axios

# User 服务
openapi --input http://localhost:8101/api/user/v2/api-docs --output  ./generated/user --client axios

# Question 服务
openapi --input http://localhost:8101/api/question/v2/api-docs --output  ./generated/question --client axios

```
### Compiles and minifies for production

```
npm run build
```

### Lints and fixes files

```
npm run lint
```

### Customize configuration

See [Configuration Reference](https://cli.vuejs.org/config/).
