let detailEditMode = false;

document.addEventListener('DOMContentLoaded', function () {
  const btnShowForm = document.getElementById('btn-show-form');
  const formContainer = document.getElementById('write-form-container');
  const btnCancel = document.getElementById('btn-cancel');
  const newsForm = document.getElementById('news-form');

  btnShowForm.addEventListener('click', function () {
    document.getElementById('news-detail-view').style.display = 'none';
    formContainer.style.display = 'block';
    btnShowForm.style.display = 'none';
  });

  btnCancel.addEventListener('click', function () {
    formContainer.style.display = 'none';
    newsForm.reset();
    btnShowForm.style.display = 'inline-block';
  });

  newsForm.addEventListener('submit', function (e) {
    e.preventDefault();

    const formData = new FormData(newsForm);

    fetch('news/write', {
      method: 'POST',
      body: formData
    })
      .then(response => {
        if (response.ok) {
          alert('뉴스가 성공적으로 작성되었습니다.');
          location.reload();
        } else {
          alert('작성 중 오류가 발생했습니다!');
        }
      })
      .catch(error => {
        console.error('Error: ', error);
        alert('서버와 통신 중 오류가 발생했습니다!');
      });
  });
});

function setDetailReadonly(readonly) {
  document.getElementById('view-title').readOnly = readonly;
  document.getElementById('view-writer').readOnly = readonly;
  document.getElementById('view-content').readOnly = readonly;
}

function showDetail(id) {
  document.getElementById('btn-show-form').style.display = 'none';
  document.getElementById('write-form-container').style.display = 'none';
  detailEditMode = false;
  setDetailReadonly(true);

  fetch(`/news/getDetail?id=${id}`)
    .then(response => response.json())
    .then(data => {
      document.getElementById('view-id').value = data.id;
      document.getElementById('view-title').value = data.title;
      document.getElementById('view-writer').value = data.writer;
      document.getElementById('view-content').value = data.content;
      document.getElementById('news-detail-view').style.display = 'block';
    })
    .catch(error => console.error('Error: ', error));
}

function closeDetail() {
  detailEditMode = false;
  setDetailReadonly(true);
  document.getElementById('news-detail-view').style.display = 'none';
  document.getElementById('btn-show-form').style.display = 'inline-block';
}

function enableEditMode() {
  detailEditMode = true;
  setDetailReadonly(false);
  document.getElementById('view-title').focus();
}

function confirmDetail() {
  if (detailEditMode) {
    document.getElementById('detail-form').submit();
    return;
  }

  closeDetail();
}

function deleteNews() {
  const id = document.getElementById('view-id').value;

  if (!id) {
    return;
  }

  location.href = `/news/delete?id=${id}`;
}
