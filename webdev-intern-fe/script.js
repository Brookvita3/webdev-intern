const BASE_URL = '/api';

// Hàm gọi API và hiển thị dữ liệu cho Search Scores
async function fetchStudentData(registrationNumber) {
    try {
        console.log('Fetching data for registration:', registrationNumber);
        const url = `${BASE_URL}/students/search?registration=${encodeURIComponent(registrationNumber)}`;
        console.log('Request URL:', url);

        const response = await fetch(url, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });

        console.log('Response status:', response.status);
        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }

        const result = await response.json();
        console.log('API response:', result);

        if (result.status === 200 && result.data) {
            displayStudentDetails(result.data);
        } else {
            displayError('No student found or server error!');
        }
    } catch (error) {
        console.error('Error fetching data:', error);
        displayError('Error fetching data: ' + error.message);
    }
}

// Hàm hiển thị chi tiết điểm số cho Search Scores
function displayStudentDetails(student) {
    const studentInfo = document.getElementById('student-info');
    const studentScoresTable = document.getElementById('student-scores');
    studentInfo.textContent = `Scores for student with registration number ${student.sbd}`;
    document.getElementById('search-error').textContent = '';

    let tableHTML = '';
    tableHTML += `<tr><td>Math</td><td>${student.toan || 'N/A'}</td><td>${getGrade(student.toan)}</td></tr>`;
    tableHTML += `<tr><td>Literature</td><td>${student.nguVan || 'N/A'}</td><td>${getGrade(student.nguVan)}</td></tr>`;
    tableHTML += `<tr><td>Foreign Language</td><td>${student.ngoaiNgu || 'N/A'}</td><td>${getGrade(student.ngoaiNgu)}</td></tr>`;
    tableHTML += `<tr><td>Physics</td><td>${student.vatLi || 'N/A'}</td><td>${getGrade(student.vatLi)}</td></tr>`;
    tableHTML += `<tr><td>Chemistry</td><td>${student.hoaHoc || 'N/A'}</td><td>${getGrade(student.hoaHoc)}</td></tr>`;
    tableHTML += `<tr><td>Biology</td><td>${student.sinhHoc || 'N/A'}</td><td>${getGrade(student.sinhHoc)}</td></tr>`;
    tableHTML += `<tr><td>History</td><td>${student.lichSu || 'N/A'}</td><td>${getGrade(student.lichSu)}</td></tr>`;
    tableHTML += `<tr><td>Geography</td><td>${student.diaLi || 'N/A'}</td><td>${getGrade(student.diaLi)}</td></tr>`;
    tableHTML += `<tr><td>Civic Education</td><td>${student.gdcd || 'N/A'}</td><td>${getGrade(student.gdcd)}</td></tr>`;

    studentScoresTable.innerHTML = tableHTML;
}

// Hàm hiển thị lỗi cho Search Scores
function displayError(message) {
    document.getElementById('search-error').textContent = message;
    document.getElementById('student-scores').innerHTML = '';
    document.getElementById('student-info').textContent = 'Detailed view of search scores here!';
}

// Hàm tính grade dựa trên điểm số
function getGrade(score) {
    if (score === null || score === undefined) return 'N/A';
    if (score >= 8) return 'Excellent';
    if (score >= 6.5) return 'Good';
    if (score >= 5) return 'Average';
    return 'Poor';
}

// Hàm gọi API và hiển thị dữ liệu cho Dashboard (Top Students)
async function fetchTopStudentsData() {
    try {
        const url = `${BASE_URL}/students/dashboard/top10/group-a`;
        console.log('Request URL:', url);

        const response = await fetch(url, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });

        console.log('Response status:', response.status);
        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }

        const result = await response.json();
        console.log('API response:', result);

        if (result.status === 200 && result.data) {
            displayDashboardData(result.data);
        } else {
            displayDashboardError('Failed to load top students data.');
        }
    } catch (error) {
        console.error('Error fetching top students data:', error);
        displayDashboardError('Error fetching top students data: ' + error.message);
    }
}

// Hàm hiển thị dữ liệu Dashboard
function displayDashboardData(students) {
    const tableBody = document.getElementById('top-students-table');
    tableBody.innerHTML = '';

    students.forEach(student => {
        const totalScore = (student.toan + student.li + student.hoa).toFixed(2);
        const row = `
            <tr>
                <td>${student.sbd}</td>
                <td>${student.toan}</td>
                <td>${student.li}</td>
                <td>${student.hoa}</td>
                <td>${totalScore}</td>
            </tr>
        `;
        tableBody.innerHTML += row;
    });
}

// Hàm hiển thị lỗi cho Dashboard
function displayDashboardError(message) {
    const tableBody = document.getElementById('top-students-table');
    tableBody.innerHTML = `<tr><td colspan="5">${message}</td></tr>`;
}

// Hàm gọi API và hiển thị báo cáo
async function fetchReportData(subject) {
    try {
        const url = `${BASE_URL}/students/report?subject=${encodeURIComponent(subject)}`;
        console.log('Fetching report for subject:', subject);
        console.log('Request URL:', url);

        const response = await fetch(url, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });

        console.log('Response status:', response.status);
        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }

        const result = await response.json();
        console.log('API response:', result);

        if (result.status === 200 && result.data) {
            displayReportData(result.data);
        } else {
            displayReportError('Failed to load report data.');
        }
    } catch (error) {
        console.error('Error fetching report data:', error);
        displayReportError('Error fetching report data: ' + error.message);
    }
}

// Hàm hiển thị dữ liệu báo cáo dưới dạng biểu đồ
function displayReportData(report) {
    console.log('Displaying report data:', report);
    const ctx = document.getElementById('subject-chart').getContext('2d');

    // Xóa biểu đồ cũ nếu có
    if (window.reportChart) {
        window.reportChart.destroy();
    }

    window.reportChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ['Level 1', 'Level 2', 'Level 3', 'Level 4'],
            datasets: [{
                label: `Score Distribution for ${report.subject}`,
                data: [
                    report.subjectScoreLevelDTO.getLevel1,
                    report.subjectScoreLevelDTO.getLevel2,
                    report.subjectScoreLevelDTO.getLevel3,
                    report.subjectScoreLevelDTO.getLevel4
                ],
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true,
                    title: {
                        display: true,
                        text: 'Number of Students'
                    }
                },
                x: {
                    title: {
                        display: true,
                        text: 'Score Levels'
                    }
                }
            },
            plugins: {
                title: {
                    display: true,
                    text: `Report for ${report.subject.toUpperCase()}`
                }
            }
        }
    });
}

// Hàm hiển thị lỗi cho báo cáo
function displayReportError(message) {
    const ctx = document.getElementById('subject-chart').getContext('2d');
    if (window.reportChart) {
        window.reportChart.destroy();
    }
    ctx.canvas.parentNode.style.display = 'block';
    ctx.fillText(message, 10, 50);
}

// Hàm điều hướng giữa các page
function showPage(pageId) {
    const pages = ['dashboard-page', 'search-page', 'reports-page', 'settings-page'];
    pages.forEach(id => {
        document.getElementById(id).style.display = 'none';
    });
    document.getElementById(pageId).style.display = 'block';

    document.querySelectorAll('.nav-item').forEach(item => {
        item.classList.remove('active');
        if (item.getAttribute('data-page') === pageId.replace('-page', '')) {
            item.classList.add('active');
        }
    });

    if (pageId === 'dashboard-page') {
        fetchTopStudentsData();
    } else if (pageId === 'reports-page') {
        // Gọi API với môn mặc định khi vào trang
        const subjectSelect = document.getElementById('subject-select');
        const defaultSubject = subjectSelect.value;
        fetchReportData(defaultSubject);
    }
}

// Gắn sự kiện change cho dropdown chỉ một lần
document.addEventListener('DOMContentLoaded', () => {
    const subjectSelect = document.getElementById('subject-select');
    subjectSelect.addEventListener('change', () => {
        const selectedSubject = subjectSelect.value;
        console.log('Subject changed to:', selectedSubject);
        fetchReportData(selectedSubject);
    });
});

// Xử lý sự kiện click cho các nút điều hướng
document.querySelectorAll('.nav-item').forEach(button => {
    button.addEventListener('click', () => {
        const page = button.getAttribute('data-page');
        showPage(`${page}-page`);
    });
});

// Xử lý nút Submit trong Search Scores
document.getElementById('search-button').addEventListener('click', () => {
    const regNumber = document.getElementById('registration').value.trim();
    if (regNumber) {
        fetchStudentData(regNumber);
    } else {
        displayError('Please enter a registration number!');
    }
});

// Hiển thị trang mặc định khi tải
window.onload = () => showPage('search-page');