# Project Demo EMBL-EBI by @NamPham

The project is final assigment from EMBL to Nam Pham. That provide the RESTful API to support:
- A mouse gene resource that returns mouse gene symbols and synonyms
- A resource that returns the relationships between mouse and human genes

## How to build

### The project development on

- Java version "11.0.12" 2021-07-20 LTS
- IntelliJ IDEA 2019.1. Please note install lombok plugin before open project on IntelliJ
- Spring Boot version 2.5.2

### Build and run

Quick run:
```http
java -jar project-demo-run.jar
```

Run step by step 

Step 1:  Build project with mavent
```http
mvn clean package -U
```
Step 2:  Start docker compose file.
```http
docker-compose up -d
```

Step 3:  Start app.
```http
java -jar target/project-demo-five.jar
```


## API Reference

#### 1. Get Mouse gene detail by id

```http
  GET /api/v1/mouse-gene/{id}
  Response
    {
        "id": 2305,
        "name": "fibroblast growth factor 8",
        "symbol": "Fgf8",
        "mgi_gene_acc_id": "MGI:99604"
    }
```

| PathVariable | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `Long` | **Required**. The mouse gene id |


#### 2. Mouse gene detail with mouse gene symbols and synonyms by query param `symbol`.

```http
  GET /api/v1/mouse-gene?symbol={symbol}
  Response
    {
        "id": 2305,
        "name": "fibroblast growth factor 8",
        "symbol": "Fgf8",
        "mgi_gene_acc_id": "MGI:99604",
        "synonyms": [
            "Fgf-8",
            "Aigf"
        ]
    }
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `symbol`      | `string` | **Required**. The Mouse gene's symbol. e.g. Fgf8 |


#### 3. Mouse gene detail with mouse gene symbols and synonyms by query param `mgi_gene_acc_id`.

```http
  GET /api/v1/mouse-gene?mgi_gene_acc_id={mgi_gene_acc_id}
  Response
    {
        "id": 2305,
        "name": "fibroblast growth factor 8",
        "symbol": "Fgf8",
        "mgi_gene_acc_id": "MGI:99604",
        "synonyms": [
            "Fgf-8",
            "Aigf"
        ]
    }
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `mgi_gene_acc_id`      | `string` | **Required**. The Mouse gene's mgi_gene_acc_id. e.g. MGI:99604 |


#### 4. Mouse gene detail with mouse gene symbols and synonyms by query param `synonym`.

```http
  GET /api/v1/mouse-gene?synonym={synonym}
  Response
    {
        "id": 2305,
        "name": "fibroblast growth factor 8",
        "symbol": "Fgf8",
        "mgi_gene_acc_id": "MGI:99604",
        "synonyms": [
            "Fgf-8",
            "Aigf"
        ]
    }
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `synonym`      | `string` | **Required**. The Mouse gene's synonym. e.g. Aigf |


#### 5. Mouse gene detail and relation between mouse and human genes by query param `symbol`

```http
  GET /api/v1/mouse-gene/relations?symbol={symbol}
  Response
    {
        "id": 2305,
        "name": "fibroblast growth factor 8",
        "symbol": "Fgf8",
        "mgi_gene_acc_id": "MGI:99604",
        "human_gene_relation_details": [
            {
            "human_gene": {
                "human_gene_id": "9024"
            },
            "support_count": 12
            },
            {
            "human_gene": {
                "human_gene_id": "9040"
            },
            "support_count": 1
            },
            {
            "human_gene": {
                "human_gene_id": "9041"
            },
            "support_count": 1
            }
        ]
    }
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `symbol`      | `string` | **Required**. The Mouse gene's symbol. e.g. Fgf8 |

#### 6. Mouse gene detail and relation between mouse and human genes by query param `mgi_gene_acc_id`

```http
  GET /api/v1/mouse-gene/relations?mgi_gene_acc_id={mgi_gene_acc_id}
  Response
    {
        "id": 2305,
        "name": "fibroblast growth factor 8",
        "symbol": "Fgf8",
        "mgi_gene_acc_id": "MGI:99604",
        "human_gene_relation_details": [
            {
            "human_gene": {
                "human_gene_id": "9024"
            },
            "support_count": 12
            },
            {
            "human_gene": {
                "human_gene_id": "9040"
            },
            "support_count": 1
            },
            {
            "human_gene": {
                "human_gene_id": "9041"
            },
            "support_count": 1
            }
        ]
    }
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `mgi_gene_acc_id`      | `string` | **Required**. The Mouse gene's mgi_gene_acc_id. e.g. MGI:99604 |

#### 7. Please refer to API document to get more information. (Need to start app before access to documentation)

 [Local API documentation](http://localhost:8080/ext/v1/documentation.html)

