<template>
    <div class="container">
        <div class="content">
            欢迎回来！
        </div>
    </div>
</template>

<script>
    export default {
        name: 'HomePage'
    };
</script>

<style scoped>
    .container {
        background-color: rgb(255, 255, 255);
        display: grid;
        place-items: center; /* 水平和垂直居中 */
        height: 100vh; /* 使容器高度等于视口高度 */
    }

    .content {
        padding: 40px;
        color: rgb(72, 72, 72);
        text-align: center;
        font-size: 80px;
        letter-spacing: 20px;
    }
</style>