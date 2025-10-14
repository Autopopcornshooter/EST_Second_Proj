function submitSearch() {
  const keyword = document.getElementById('keywordInput').value.trim();
  if (!keyword) return;
  window.location.href = `/admin/users/search/${encodeURIComponent(keyword)}/1`;
}