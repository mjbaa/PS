select b.id, a.fish_name, b.length
from fish_name_info as a
    join (
        select id, fish_type, length, rank() over(
            partition by fish_type
            order by length desc) as rvalue
        from fish_info 
         ) as b
    on a.fish_type = b.fish_type
where b.rvalue=1
order by b.id


