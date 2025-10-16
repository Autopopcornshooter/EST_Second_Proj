document.addEventListener("DOMContentLoaded", () => {
    const labels = JSON.parse(regionLabels);
    const data = JSON.parse(regionData);

    const ctx = document.getElementById('regionChart').getContext('2d');

    new Chart(ctx, {
        type: 'pie',
        data: {
            labels: labels,
            datasets: [{
                data: data,
                backgroundColor: ['#FFCE56', '#36A2EB', '#FF6384', '#8BC34A', '#FF9800'],
                borderColor: '#ffffff',
                borderWidth: 2
            }]
        },
        options: {
            plugins: {
                title: {
                    display: true,
                    text: '지역별 가게 수 통계',
                    font: { size: 16 }
                },
                legend: {
                    position: 'bottom'
                }
            }
        }
    });

    const regionList = document.getElementById('regionList');
    labels.forEach((label, idx) => {
        const li = document.createElement('li');
        li.textContent = `${label} : (${data[idx]})`;
        regionList.appendChild(li);
    });
});
