<script lang="ts">
    // TypeScript code goes here (if needed)
    let activeLink: string = 'home'; // You can manage the active link state
</script>

<style>
    nav {
        display: flex;
        justify-content: space-around;
        background-color: #333;
        padding: 1rem;
    }
    a {
        color: white;
        text-decoration: none;
        padding: 0.5rem;
    }
    a.active {
        font-weight: bold;
        border-bottom: 2px solid #fff;
    }
    a:hover {
        background-color: #444;
    }
</style>

<nav>
    <a href="/" class={activeLink === 'home' ? 'active' : ''} on:click={() => activeLink = 'home'}>Home</a>
    <a href="/register" class={activeLink === 'register' ? 'active' : ''} on:click={() => activeLink = 'register'}>Register</a>
    <a href="/refresh" class={activeLink === 'refresh' ? 'active' : ''} on:click={() => activeLink = 'refresh'}>refresh</a>
</nav>
