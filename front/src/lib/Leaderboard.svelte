<script lang="ts">
    import { onMount } from "svelte";

    type Climber = {
        id: number;
        kilterId: string;
        username: string;
        name: string;
        score: number;
        grade: number;
    };

    let leaderboard: Climber[] = [];
    let updated: boolean = false;
    let loading: boolean = true;

    async function fetchLeaderboard() {
        try {
            const res = await fetch("https://api.kilterleaderboard.de/users/");
            if (!res.ok) throw new Error("Failed to fetch Leaderboard!");
            leaderboard = await res.json();
        } catch (error) {
            console.error(error);
        }
        leaderboard.sort((a: Climber, b: Climber) => b.score - a.score);
        loading = false;
        if ( !updated ) {
            updated = true;
            const updateTimeout = setTimeout(() => {
                //fetchLeaderboard();
                updateLeaderboard();
                clearTimeout(updateTimeout);
            }, 7000);
        }
    }

    async function updateLeaderboard(){
        try{
            const res = await fetch("https://api.kilterleaderboard.de/users/update", {method: "PATCH"});
            if (!res.ok) console.log("The last update was less than 5 minutes ago");
            await fetchLeaderboard();
        } catch (error) {
            console.log(error)
        }
    }

    interface Dictionary<T> {
        [key: number]: T;
    }

    function difficultyInFbScala(difficulty: number) {
        const mapping: Dictionary<String> = {
            10: '4a',
            11: '4b',
            12: '4c',
            13: '5a',
            14: '5b',
            15: '5c',
            16: '6a',
            17: '6a+',
            18: '6b',
            19: '6b+',
            20: '6c',
            21: '6c+',
            22: '7a',
            23: '7a+',
            24: '7b',
            25: '7b+',
            26: '7c',
            27: '7c+',
            28: '8a',
            29: '8a+',
            30: '8b',
            31: '8b+',
            32: '8c',
            33: '8c+'
        };

        return mapping[difficulty] || 'not a valid difficulty';
    }

    onMount(fetchLeaderboard);
</script>

<div>
    {#if loading}
        <h4>Loading...</h4>
    {:else}
        <table>
            <thead>
                <tr>
                    <th>Rank</th>
                    <th>Name</th>
                    <th>Level</th>
                    <th>Score</th>
                </tr>
            </thead>
            <tbody>
                {#each leaderboard as player, index}
                    <tr>
                        <td>{index+1}</td>
                        <td>{player.username}</td>
                        <td>{difficultyInFbScala(player.grade)}</td>
                        <td>{player.score}</td>
                    </tr>
                {/each}
            </tbody>
        </table>
    {/if}
</div>

<style>
    h4 {
        align-content: center;
        text-align: center;
        margin: 13em auto;
    }

    table {
        width: 100%;
        border-collapse: collapse;
    }
    th, td {
        border: 1px solid #ddd;
        padding: 8px;
        text-align: left;
    }
    th {
        background-color: #f4f4f4;
    }
</style>