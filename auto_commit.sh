#!/bin/bash

# 1. Captura a mensagem passada como argumento. Se estiver vazia, define um padrão.
MENSAGEM_COMMIT=${1:-"wip: daily technical contribution"}

echo "----------------------------------------"
echo "🚀 Iniciando automação de deploy no Git..."
echo "----------------------------------------"

# 2. Adiciona todas as modificações no Staging Area
echo "📦 Adicionando arquivos..."
git add .

# 3. Executa o commit utilizando a mensagem capturada
echo "✍️ Gravando o commit com a mensagem: '$MENSAGEM_COMMIT'..."
git commit -m "$MENSAGEM_COMMIT"

# 4. Envia as alterações para o repositório remoto (ajuste 'main' se sua branch for 'master')
echo "📤 Enviando dados para o GitHub..."
git push origin main

echo "----------------------------------------"
echo "✅ Processo concluído com sucesso!"
echo "----------------------------------------"
