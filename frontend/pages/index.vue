<template>
  <div class="container mx-auto p-8">
    <div class="text-center">
      <h1 class="text-4xl font-bold mb-4">Welcome to Nuxt Frontend</h1>
      <p class="text-gray-600 mb-8">Connected to Spring Boot Backend</p>
      
      <UCard>
        <template #header>
          <h2 class="text-xl font-semibold">Backend Health Status</h2>
        </template>
        
        <div v-if="pending" class="text-center">
          <UIcon name="i-heroicons-arrow-path" class="animate-spin" />
          <p>Checking backend...</p>
        </div>
        
        <div v-else-if="error" class="text-red-500 text-center">
          <UIcon name="i-heroicons-x-circle" />
          <p>Failed to connect to backend</p>
        </div>
        
        <div v-else class="text-green-500 text-center">
          <UIcon name="i-heroicons-check-circle" />
          <p>Backend is {{ data?.status }}</p>
        </div>
      </UCard>
    </div>
  </div>
</template>

<script setup>
const config = useRuntimeConfig()

const { data, pending, error } = await $fetch(`${config.public.apiBase}/health`)
  .catch(() => ({ data: null, pending: false, error: true }))
</script>