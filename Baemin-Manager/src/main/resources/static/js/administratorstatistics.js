const ctx = document.getElementById('regionChart');
new Chart(ctx, {
    type: 'pie',
    data: {
        labels: ['서울특별시', '인천광역시', '부산광역시'],
        datasets: [{
            data: [3300, 1500, 1800],
            backgroundColor: ['#FFCE56', '#36A2EB', '#FF6384'],
            borderColor: '#ffffff',
            borderWidth: 2,
        }]
    },
    options: {
        plugins: {
            title: {
                display: true,
                text: '지역별 주문 통계',
                font: { size: 16 }
            },
            legend: {
                position: 'bottom'
            }
        }
    }
});