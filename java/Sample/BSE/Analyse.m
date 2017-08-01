fid = fopen('Bsizer.log');
B = textscan(fid, '%f,%f');
C = [B{1} B{2}];
x = C(:, 1);
y = C(:, 2);
plot(x, y);
