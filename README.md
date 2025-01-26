# SimpleBank Java Application

## Prerequisites
- Java 17+
- Maven

## Getting Started

### Clone the Repository
```bash
git clone https://github.com/TiagoLustosa/simplebankJava
cd simplebankJava
```

### Build and Run

1. Build the project
```bash
./mvnw clean install
```

2. Run the application
```bash
./mvnw spring-boot:run
```

### Application Details
- **Base URL**: `http://localhost:8080`

## API Endpoints

### Users Creation

**Endpoint**: `POST /users`

#### Create Common User (Can Send Money)
```json
{
    "fullName": "Karen Smith",
    "document": "055.585.788.07",
    "email": "karen-smith@email.com",
    "password": "789654",
    "userType": "COMMON",
    "balance": 1000
}
```

#### Create Merchant User (Can Receive Money)
```json
{
    "fullName": "Jhon Doe",
    "document": "078.958.124.07",
    "email": "jhon-doe@email.com",
    "password": "543678",
    "userType": "MERCHANT",
    "balance": 10
}
```

### Transactions

**Endpoint**: 
- `POST /transactions`: Create Transaction
- `GET /transactions`: List Transactions

#### Create Transaction
```json
{
    "senderDocument": "055.585.788.07",
    "receiverDocument": "078.958.124.07",
    "amount": 25
}
```

## Important Notes
- Transaction authorization is based on external third-party systems
- Some transactions might fail due to random authorization or notification service issues
- If transactions fail repeatedly, retry the request

## Troubleshooting
- Ensure all dependencies are correctly installed
- Check network connectivity for external service calls
- Verify user documents and account balances before transactions
